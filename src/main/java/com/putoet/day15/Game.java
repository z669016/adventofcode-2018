package com.putoet.day15;

import com.putoet.grid.Point;
import com.putoet.search.GenericSearch;
import org.javatuples.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {
    private static final List<Point> DIRECTIONS = Point.directions(true);

    private static final char WALL = '#';
    private static final char EMPTY = '.';

    private final char[][] grid;
    private final List<Unit> units;

    public Game(char[][] grid, List<Unit> units) {
        this.grid = grid;
        this.units = units;

        if (units.stream().anyMatch(u -> !contains(u.point())))
            throw new IllegalArgumentException("One of the units is off the grid");
    }

    private boolean contains(Point point) {
        return point.x >= 0 && point.x < grid[0].length && point.y >=0 && point.x<grid.length;
    }

    public Game move(Unit unit, Point to) {
        paintUnit(unit, EMPTY);
        unit.move(to);
        paintUnit(unit);

        return this;
    }

    private Game paintUnit(Unit unit) {
        return paintUnit(unit, unit.type().toChar());
    }

    private Game paintUnit(Unit unit, char c) {
        assert contains(unit.point());

        grid[unit.point().y][unit.point().x] = c;
        return this;
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        for (int y = 0; y < grid.length; y++) {
            if (y > 0) sb.append("\n");

            sb.append(String.valueOf(grid[y]));
            sb.append(unitsForLine(y));
        }

        return sb.toString();
    }

    private String unitsForLine(int y) {
        final List<Unit> unitsForLine = units.stream()
                .filter(Unit::alive)
                .filter(u -> u.point().y == y)
                .sorted(Comparator.comparing(u ->u.point().x))
                .collect(Collectors.toList());

        return unitsForLine.isEmpty() ? "" : "   " +
                unitsForLine.stream()
                        .map(Unit::toString)
                        .collect(Collectors.joining(", "));
    }

    public Pair<UnitType, Integer> combat() {
        int rounds = 0;
        while (round()) {
            rounds++;
        }

        System.out.println(this);
        System.out.println("Game finished after " + rounds + " rounds");
        System.out.println("Alternative score is " + ((rounds-1) * units.stream().mapToInt(Unit::hitPoints).sum()));

        return new Pair<>(winner(), rounds * units.stream().mapToInt(Unit::hitPoints).sum());
    }

    private boolean round() {
        boolean done = false;
        units.sort(Comparator.comparing((Unit u) -> u.point().y).thenComparing(u -> u.point().x));

        for(Unit attacker : units) {
            if (!attacker.alive())
                continue;;

            //Select all possible targets
            final List<Unit> targets = targets(attacker);

            // quit when there are no more  targets left
            done = targets.isEmpty();
            if (done) break;

            // find adjacent target
            Optional<Unit> targetInRange = targetInRange(attacker,targets);
            if (targetInRange.isEmpty()) {
                // Move closer to a target
                final List<Point> inRange = inRange(targets);
                final Optional<Point> to = moveTo(attacker, inRange);
                to.ifPresent(p -> move(attacker, p));
            }

            // find adjacent target and attack
            targetInRange = targetInRange(attacker,targets);
            targetInRange.ifPresent(target -> {
                target.defend(attacker);
                if (!target.alive())
                    grid[target.point().y][target.point().x] = EMPTY;
            });
        }

        return !done;
    }

    private List<Unit> targets(Unit attacker) {
        return units.stream()
                .filter(u ->u.type() != attacker.type())
                .filter(Unit::alive)
                .collect(Collectors.toList());
    }

    private Optional<Unit> targetInRange(Unit attacker, List<Unit> targets) {
        return targets.stream()
                .filter(t -> t.point().manhattanDistance(attacker.point()) == 1)
                .min(Comparator.comparing(Unit::hitPoints).thenComparing(Unit::point));
    }

    private List<Point> inRange(List<Unit> targets) {
        return targets.stream().map(this::inRange).flatMap(List::stream).collect(Collectors.toList());
    }

    private List<Point> inRange(Unit target) {
        return DIRECTIONS.stream()
                .map(d -> d.add(target.point()))
                .filter(this::contains)
                .filter(p -> grid[p.y][p.x] == EMPTY)
                .collect(Collectors.toList());
    }

    private Optional<Point> moveTo(Unit attacker, List<Point> inRange) {
        final Optional<GenericSearch.Node<Point>> route = inRange.stream()
                .map(pointInRange -> GenericSearch.bfs(attacker.point(),
                        p -> p.equals(pointInRange),
                        p -> DIRECTIONS.stream()
                                .map(d -> d.add(p))
                                .filter(this::contains)
                                .filter(point -> grid[point.y][point.x] == EMPTY)
                                .collect(Collectors.toList())
                ))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .min(Comparator.comparing((Function<GenericSearch.Node<Point>, Integer>) GenericSearch.Node::steps).thenComparing((GenericSearch.Node<Point> node) -> node.state));

        return route.map(node -> node.path().get(1));
    }

    private UnitType winner() {
        return units.stream().filter(u -> u.hitPoints() > 0).findFirst().get().type();
    }
}
