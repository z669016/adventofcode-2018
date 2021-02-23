package com.putoet.day15;

import com.putoet.grid.Point;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class BeverageBanditsBoard {
    public static final List<Point> NEIGHBOURS = List.of(Point.EAST, Point.SOUTH, Point.NORTH, Point.WEST);

    public static final char SPACE = '.';
    public static final char WALL = '#';

    private final char[][] board;
    private final Queue<Unit> units;

    public BeverageBanditsBoard(char[][] board, Queue<Unit> units) {
        this.board = board;
        this.units = units;
    }

    public static BeverageBanditsBoard of(List<String> lines) {
        assert lines != null && lines.size() > 0;

        final char[][] board = new char[lines.size()][];
        final PriorityQueue<Unit> units = new PriorityQueue<>();

        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            board[y] = new char[line.length()];

            for (int x = 0; x < line.length(); x++) {
                char token = line.charAt(x);
                if (token == Elve.TOKEN || token == Goblin.TOKEN) {
                    units.offer(UnitFactory.of(x, y, token));
                    token = '.';
                }
                if (token != '#' && token != '.')
                    throw new IllegalArgumentException("Invalid token at position " + x + ", " + y);

                board[y][x] = token;
            }
        }

        return new BeverageBanditsBoard(board, units);
    }

    public char[][] board() {
        return board;
    }

    public Queue<Unit> units() {
        return new PriorityQueue<>(units);
    }

    public boolean offTheBoard(Point point) {
        assert point != null;

        return point.x < 0 ||
                point.y < 0 ||
                point.y >= board.length ||
                point.x >= board[point.y].length;
    }

    public boolean isNotWall(Point point) {
        if (offTheBoard(point)) return false;

        return board[point.y][point.x] != WALL;
    }

    public boolean isOpen(Point point) {
        if (offTheBoard(point)) return false;

        return board[point.y][point.x] == SPACE && unitAt(point).isEmpty();
    }

    public List<Unit> targets(Unit current) {
        return units.stream()
                .filter(unit -> unit.token() != current.token())
                .filter(Unit::isAlive)
                .collect(Collectors.toList());
    }

    private Optional<Unit> unitAt(Point point) {
        return units.stream()
                .filter(Unit::isAlive)
                .filter(unit -> unit.location().equals(point))
                .findFirst();
    }

    public List<Point> inRange(Point location) {
        return NEIGHBOURS.stream()
                .map(location::add)
                .filter(this::isOpen)
                .collect(Collectors.toList());
    }

    public List<Point> nextTo(Point location) {
        return NEIGHBOURS.stream()
                .map(location::add)
                .filter(this::isNotWall)
                .collect(Collectors.toList());
    }

    public void print() {
        for (int y = 0; y < board.length; y++) {
            System.out.printf("%2d ", y);
            for (int x = 0; x < board[y].length; x++) {
                final Optional<Unit> unit = unitAt(Point.of(x, y));
                System.out.print(unit.isPresent() ? unit.get().token() : board[y][x]);
            }
            System.out.println();
        }

        for (Unit unit : units)
            System.out.println(unit);
    }

    public long score() {
        return units.stream().filter(Unit::isAlive).mapToInt(Unit::hitPoints).sum();
    }
}
