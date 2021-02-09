package com.putoet.day15;

import com.putoet.grid.Point;

import java.util.*;
import java.util.stream.Collectors;

public class BeverageBanditsBoard {
    public static final List<Point> neighbours = List.of(Point.NORTH, Point.WEST, Point.SOUTH, Point.EAST);

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

    public char[][] board() { return board; }

    public Queue<Unit> units() {
        return units.stream()
                .filter(Unit::isAlive)
                .collect(Collectors.toCollection(PriorityQueue::new));
    }

    public boolean offTheBoard(int x, int y) {
        if (x < 0 || y < 0) return true;

        return y >= board.length || x >= board[y].length;
    }

    public boolean isWall(int x, int y) {
        if (offTheBoard(x, y)) return true;

        return board[y][x] == WALL;
    }

    public boolean isOpen(int x, int y) {
        if (offTheBoard(x, y)) return false;

        return board[y][x] == SPACE && at(x,y).isEmpty();
    }

    private Optional<Unit> at(int x, int y) {
        final Point point = Point.of(x,y);
        return units.stream()
                .filter(Unit::isAlive)
                .filter(unit -> unit.location().equals(point))
                .findFirst();
    }

    public List<Point> inRange(Point location) {


        final List<Point> range = new ArrayList<>();
        if (isOpen(location.x-1,location.y)) range.add(Point.of(location.x-1,location.y));
        if (isOpen(location.x+1,location.y)) range.add(Point.of(location.x+1,location.y));
        if (isOpen(location.x,location.y-1)) range.add(Point.of(location.x,location.y-1));
        if (isOpen(location.x,location.y+1)) range.add(Point.of(location.x,location.y+1));

        return range;
    }

    public List<Point> nextTo(Point location) {
        final List<Point> range = new ArrayList<>();
        if (!isWall(location.x-1,location.y)) range.add(Point.of(location.x-1,location.y));
        if (!isWall(location.x+1,location.y)) range.add(Point.of(location.x+1,location.y));
        if (!isWall(location.x,location.y-1)) range.add(Point.of(location.x,location.y-1));
        if (!isWall(location.x,location.y+1)) range.add(Point.of(location.x,location.y+1));

        return range;
    }

    public void print() {
        for (int y = 0; y < board.length; y++) {
            System.out.printf("%2d ", y);
            for (int x = 0; x < board[y].length; x++) {
                final Optional<Unit> unit = at(x,y);
                System.out.print(unit.isPresent() ? unit.get().token() : board[y][x]);
            }
            System.out.println();
        }

        for (Unit unit : units)
            System.out.println(unit);
    }

    public List<Unit> enemies(Unit current) {
        return List.copyOf(units).stream()
                .filter(unit -> unit.token() != current.token())
                .filter(Unit::isAlive)
                .collect(Collectors.toList());
    }
}
