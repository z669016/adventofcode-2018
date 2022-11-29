package com.putoet.day6;

import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import com.putoet.grid.Size;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Grid {
    public static Grid of(List<Point> points) {
        assert points != null && points.size() > 0;

        final int maxX = points.stream().mapToInt(Point::x).max().orElseThrow();
        final int maxY = points.stream().mapToInt(Point::y).max().orElseThrow();
        final int size = Math.max(maxX + 2, maxY + 2);

        return new Grid(new Size(size, size), points);
    }

    private final char[][] grid;
    private final List<Point> points;

    public Grid(Size size, List<Point> points) {
        this.grid = new char[size.dy()][size.dx()];
        this.points = List.copyOf(points);

        fill();
    }

    public Size size() { return new Size(grid.length, grid.length); }

    public void fill() {
        for (char[] chars : grid) Arrays.fill(chars, '.');

        IntStream.range(0, points.size())
                .forEach(i -> {
                    final Point p = points.get(i);
                    grid[p.y()][p.x()] = asLetter(i);
                });
    }

    public void fillClosest() {
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid.length; x++) {
                final char[] closest = closest(Point.of(x, y));
                if (closest.length == 1)
                    grid[y][x] = closest[0];
            }
    }

    public long countMax(int max) {
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid.length; x++) {
                final Point point = Point.of(x, y);
                final int sum = points.stream().mapToInt(p -> p.manhattanDistance(point)).sum();
                if (sum < max)
                    grid[y][x] = '#';
            }

        return GridUtils.count(grid, '#');
    }


    private char[] closest(Point of) {
        final int[] distances = points.stream().mapToInt(of::manhattanDistance).toArray();
        final int min = Arrays.stream(distances).min().orElseThrow();
        final int count = (int) Arrays.stream(distances).filter(i -> i == min).count();
        final char[] letters = new char[count];

        for (int i = 0, c = 0; i < distances.length; i++)
            if (distances[i] == min)
                letters[c++] = asLetter(i);

        return letters;
    }

    public List<Character> enclosedLetters() {
        final List<Character> letters = IntStream.range(0, points.size())
                .mapToObj(this::asLetter)
                .toList();

        return letters.stream().
                filter(this::notAtEdge)
                .sorted()
                .toList();
    }

    private boolean notAtEdge(char letter) {
        for (char[] chars : grid)
            if ((chars[0] == letter) || (chars[grid.length - 1] == letter))
                return false;

        for (int x = 0; x < grid.length; x++)
            if ((grid[0][x] == letter) || (grid[grid.length-1][x] == letter))
                return false;

        return true;
    }

    public long count(char c) {
        return GridUtils.count(grid, c);
    }

    private char asLetter(int i) {
        return (char) (i + 'A');
    }

    public void print() {
        GridUtils.print(grid);
    }
}
