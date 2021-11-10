package com.putoet.day15;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;
import com.putoet.search.GenericSearch;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game {
    public static final char WALL = '#';
    public static final char EMPTY = '.';

    public final Grid grid;
    public final List<Unit> units;

    private int rounds = 0;

    public Game(final Grid grid) {
        this(grid, List.of());
    }

    public Game(final Grid grid, final List<Unit> units) {
        this.grid = grid;
        this.units = new ArrayList<>(units);
    }

    protected void sortUnitsReadingOrder() {
        units.sort(readingOrder());
    }

    private Comparator<Unit> readingOrder() {
        return Comparator.comparing(Unit::position);
    }

    @Override
    public String toString() {
        return grid.toString();
    }

    public int battle() {
        if (rounds == 0) {
            boolean haveTargets = true;

            while (haveTargets) {
                sortUnitsReadingOrder();
                for (Unit unit : units) {
                    if (unit.isAlive()) {
                        haveTargets = !unit.targets(units).isEmpty();
                        if (haveTargets) {
                            Optional<Unit> adjacentTarget = adjacent(unit);
                            if (adjacentTarget.isEmpty()) {
                                final Optional<GenericSearch.Node<Point>> nearest = nearest(unit);
                                nearest.ifPresent(node -> unit.move(node.path().get(1), grid));
                            }

                            adjacentTarget = adjacent(unit);
                            if (adjacentTarget.isPresent()) {
                                final Unit target = adjacentTarget.get();
                                target.attacked(unit);
                                if (!target.isAlive())
                                    grid.set(target.position().x, target.position().y, EMPTY);
                            }
                        } else
                            break;
                    }
                }
                if (haveTargets)
                    rounds++;
                dump();
            }
        }
        return score();
    }

    public void dump() {
        System.out.println(this);
        System.out.println("Rounds: " + rounds);
        System.out.println("Score: " + score());
        for (Unit unit : units) {
            System.out.println(unit.symbol() + "(" + unit.hitPoints() +")");
        }
    }

    public int score() {
        return rounds * units.stream().filter(Unit::isAlive).mapToInt(Unit::hitPoints).sum();
    }

    public Optional<Unit> adjacent(Unit unit) {
        return unit.targets(units).stream()
                .filter(Unit::isAlive)
                .filter(t -> (t.position().x == unit.position().x && Math.abs(t.position().y - unit.position().y) == 1)
                        || (t.position().y == unit.position().y && Math.abs(t.position().x - unit.position().x) == 1))
                .min(readingOrder());
    }

    public List<Point> inRange(final List<Unit> units) {
        return units.stream()
                .map(this::inRange)
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());
    }

    private List<Point> inRange(final Unit unit) {
        return inRange(unit.position());
    }

    private List<Point> inRange(final Point point) {
        return adjacent(point, p -> grid.get(p.x, p.y) == EMPTY);
    }

    private List<Point> adjacent(final Point start, final Predicate<Point> predicate) {
        return Point.directions(true).stream()
                .map(p -> p.add(start))
                .filter(p -> grid.contains(p.x, p.y))
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public Optional<GenericSearch.Node<Point>> nearest(final Unit unit) {
        final List<Unit> targets = unit.targets(units);
        final List<Point> inRange = inRange(targets);
        return nearest(unit, inRange);
    }

    public Optional<GenericSearch.Node<Point>> nearest(final Unit unit, final List<Point> inRange) {
        return inRange.stream()
                .map(p -> reachable(unit, p))
                .flatMap(Collection::stream)
                .min((first, second) -> {
                    final int firstSteps = first.steps();
                    final int secondSteps = second.steps();

                    return firstSteps != secondSteps ? Integer.compare(firstSteps, secondSteps) : first.compareTo(second);
                });
    }

    public List<GenericSearch.Node<Point>> reachable(final Unit unit, final Point inRange) {
        return GenericSearch.findAll(unit.position(), p -> p.equals(inRange), this::inRange);
    }
}
