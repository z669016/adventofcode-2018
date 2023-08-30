package com.putoet.day6;

import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import com.putoet.grid.Size;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Grid {
    public static Grid of(@NotNull List<Point> points) {
        assert !points.isEmpty();

        final var maxX = points.stream().mapToInt(Point::x).max().orElseThrow();
        final var maxY = points.stream().mapToInt(Point::y).max().orElseThrow();
        final var size = Math.max(maxX + 2, maxY + 2);

        return new Grid(new Size(size, size), points);
    }

    private static char asLetter(int i) {
        return (char) (i + 'A');
    }

    private final char[][] grid;
    private final List<Point> points;

    public Grid(@NotNull Size size, @NotNull List<Point> points) {
        this.grid = new char[size.dy()][size.dx()];
        this.points = List.copyOf(points);

        fill();
    }

    public Size size() {
        return new Size(grid.length, grid.length);
    }

    public void fill() {
        for (var chars : grid)
            Arrays.fill(chars, '.');

        IntStream.range(0, points.size())
                .forEach(i -> {
                    final var p = points.get(i);
                    grid[p.y()][p.x()] = asLetter(i);
                });
    }

    public void fillClosest() {
        for (var y = 0; y < grid.length; y++)
            for (var x = 0; x < grid.length; x++) {
                final var closest = closest(Point.of(x, y));
                if (closest.length == 1)
                    grid[y][x] = closest[0];
            }
    }

    public long countMax(int max) {
        for (var y = 0; y < grid.length; y++)
            for (var x = 0; x < grid.length; x++) {
                final var point = Point.of(x, y);
                final var sum = points.stream().mapToInt(p -> p.manhattanDistance(point)).sum();
                if (sum < max)
                    grid[y][x] = '#';
            }

        return GridUtils.count(grid, '#');
    }


    private char[] closest(Point of) {
        final var distances = points.stream().mapToInt(of::manhattanDistance).toArray();
        final var min = Arrays.stream(distances).min().orElseThrow();
        final var count = (int) Arrays.stream(distances).filter(i -> i == min).count();
        final var letters = new char[count];

        for (int i = 0, c = 0; i < distances.length; i++)
            if (distances[i] == min)
                letters[c++] = asLetter(i);

        return letters;
    }

    public List<Character> enclosedLetters() {
        return IntStream.range(0, points.size())
                .mapToObj(Grid::asLetter)
                .filter(this::notAtEdge)
                .sorted()
                .toList();
    }

    private boolean notAtEdge(char letter) {
        for (var chars : grid)
            if ((chars[0] == letter) || (chars[grid.length - 1] == letter))
                return false;

        for (var x = 0; x < grid.length; x++)
            if ((grid[0][x] == letter) || (grid[grid.length - 1][x] == letter))
                return false;

        return true;
    }

    public long count(char c) {
        return GridUtils.count(grid, c);
    }

    public void print() {
        GridUtils.print(grid);
    }
}
