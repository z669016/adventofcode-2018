package com.putoet.day15;

import com.putoet.grid.Point;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    public static long play(List<String> lines) {
        assert lines != null && lines.size() > 0;

        BeverageBanditsBoard board = BeverageBanditsBoard.of(lines);
        final long hitPoints = board.score();

        System.out.println("Initial");
        board.print();
        System.out.println();

        int rounds = 0;
        boolean combat = true;
        while (combat) {
            final Queue<Unit> units = board.units();
            while (!units.isEmpty()) {
                final Unit unit = units.poll();
                if (unit.isAlive())
                    combat = unit.turn(board);
            }

            if (combat) {
                rounds++;
                board = new BeverageBanditsBoard(board.board(), board.units());

                final long hits = hitPoints - board.score();
                System.out.println("After " + rounds + " round(s)");
                System.out.println("HitPoints " + hits + " on " + (hits/3) + " attacks");
                board.print();
                System.out.println();
            }
        }

        board.print();
        System.out.println();

        return board.score() * rounds;
    }

    public static Optional<Point> nextPointFor(Unit current, BeverageBanditsBoard board) {
        // check for alive enemies on the board
        final List<Unit> enemies = board.targets(current);
        if (enemies.size() == 0)
            return Optional.empty();

        // find all empty spaces next to an enemy
        final List<Point> inRange = enemies.stream()
                .map(unit -> board.inRange(unit.location()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        if (inRange.size() == 0)
            return Optional.empty();
        final List<Point> nearest = UnitSearch.nextPoints(current, inRange, board);


        return firstAfterCurrent(current.location(), nearest);
    }

    private static Optional<Point> firstAfterCurrent(Point current, List<Point> nearest) {
        if (nearest.isEmpty())
            return Optional.empty();

        if (nearest.size() == 1)
            return Optional.of(nearest.get(0));

        nearest.sort(Comparator.comparingInt(Unit.readingOrder(current)));
        final Optional<Point> next = nearest.stream()
                .filter(p -> p.y > current.y || (p.y == current.y && p.x >= current.x))
                .findFirst();

        return next.isPresent() ? next : Optional.of(nearest.get(0));
    }
}
