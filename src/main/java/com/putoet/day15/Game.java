package com.putoet.day15;

import com.putoet.grid.Point;
import com.putoet.search.GenericSearch;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

class Game {
    private static final List<Point> DIRECTIONS = Point.directions(true);
    private static final Comparator<Unit> unitAttackOrder = Comparator.comparing(Unit::hitPoints).thenComparing((Unit::point));
    private static final Comparator<Unit> unitReadingOrder = Comparator.comparing(Unit::point);
    private static final Comparator<Point> pointReadingOrder = Point::compareTo;

    private static final char EMPTY = '.';

    private final char[][] grid;
    private final List<Unit> units;

    public Game(char[][] grid, @NotNull List<Unit> units) {
        this.grid = grid;
        this.units = units;

        if (units.stream().anyMatch(u -> !contains(u.point())))
            throw new IllegalArgumentException("One of the units is off the grid");
    }

    public List<Unit> elves() {
        return units.stream()
                .filter(Unit::alive)
                .filter(unit -> unit.type() == UnitType.ELF)
                .collect(Collectors.toList());
    }

    private boolean contains(Point point) {
        return point.x() >= 0 && point.x() < grid[0].length && point.y() >= 0 && point.x() < grid.length;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int y = 0; y < grid.length; y++) {
            if (y > 0) sb.append("\n");

            sb.append(String.valueOf(grid[y]));
            sb.append(unitsForLine(y));
        }

        return sb.toString();
    }

    private String unitsForLine(int y) {
        final var unitsForLine = units.stream()
                .filter(Unit::alive)
                .filter(u -> u.point().y() == y)
                .sorted(unitReadingOrder).toList();

        return unitsForLine.isEmpty() ? "" : "   " +
                unitsForLine.stream()
                        .map(Unit::toString)
                        .collect(Collectors.joining(", "));
    }

    public Outcome combat() {
        var rounds = 0;
        while (round()) {
            rounds++;
        }
        return new Outcome(winner(), rounds * units.stream().mapToInt(Unit::hitPoints).sum());
    }

    private boolean round() {
        var done = false;
        units.sort(unitReadingOrder);

        for (var attacker : units) {
            // Only living units take a turn
            if (!attacker.alive())
                continue;

            //Select all possible targets/enemies for this attacker
            final var targets = targets(attacker);

            // quit when there are no more  targets left
            done = targets.isEmpty();
            if (done) break;

            // find adjacent target
            var targetAdjacent = targetAdjacent(attacker, targets);
            if (targetAdjacent.isEmpty()) {
                // Move closer to a target
                final var inRange = inRange(targets);
                final var to = moveTo(attacker, inRange);
                to.ifPresent(p -> move(attacker, p));

                // find adjacent target
                targetAdjacent = targetAdjacent(attacker, targets);
            }

            // find adjacent target and attack
            targetAdjacent.ifPresent(target -> {
                target.defend(attacker);
                if (!target.alive())
                    grid[target.point().y()][target.point().x()] = EMPTY;
            });
        }

        return !done;
    }

    private List<Unit> targets(Unit attacker) {
        return units.stream()
                .filter(u -> u.type() != attacker.type())
                .filter(Unit::alive)
                .sorted(unitReadingOrder)
                .toList();
    }

    private Optional<Unit> targetAdjacent(Unit attacker, List<Unit> targets) {
        return targets.stream()
                .filter(target -> target.point().manhattanDistance(attacker.point()) == 1)
                .min(unitAttackOrder);
    }

    private List<Point> inRange(List<Unit> targets) {
        return targets.stream()
                .map(this::inRange)
                .flatMap(List::stream)
                .sorted(pointReadingOrder)
                .toList();
    }

    private List<Point> inRange(Unit target) {
        return DIRECTIONS.stream()
                .map(direction -> direction.add(target.point()))
                .filter(this::contains)
                .filter(point -> grid[point.y()][point.x()] == EMPTY)
                .sorted(pointReadingOrder)
                .toList();
    }

    public Game move(@NotNull Unit unit, @NotNull Point to) {
        assert unit.alive();
        assert unit.point().manhattanDistance(to) == 1;

        paintUnit(unit, EMPTY);
        unit.move(to);
        paintUnit(unit);

        return this;
    }

    private void paintUnit(Unit unit) {
        paintUnit(unit, unit.type().toChar());
    }

    private void paintUnit(Unit unit, char c) {
        assert contains(unit.point());

        grid[unit.point().y()][unit.point().x()] = c;
    }

    private Optional<Point> moveTo(Unit attacker, List<Point> inRange) {
        final var route = inRange.stream()
                .map(pointInRange -> GenericSearch.bfs(
                        attacker.point(),
                        p -> p.equals(pointInRange),
                        p -> DIRECTIONS.stream()
                                .map(direction -> direction.add(p))
                                .filter(this::contains)
                                .filter(point -> grid[point.y()][point.x()] == EMPTY)
                                .sorted(pointReadingOrder)
                                .collect(Collectors.toList())
                ))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .min(Comparator.comparing((Function<GenericSearch.Node<Point>, Integer>) GenericSearch.Node::steps).thenComparing((GenericSearch.Node<Point> node) -> node.state));

        return route.map(node -> node.path().get(1));
    }

    private UnitType winner() {
        return units.stream().filter(u -> u.hitPoints() > 0).findFirst().orElseThrow().type();
    }
}
