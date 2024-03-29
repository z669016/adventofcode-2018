package com.putoet.day10;

import com.putoet.grid.Size;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Message {
    private final List<MovingPoint> movingPoints;
    private int decryptTime = 0;
    private Message(@NotNull List<MovingPoint> movingPoints) {
        assert !movingPoints.isEmpty();

        this.movingPoints = movingPoints;
    }

    public static Message of(@NotNull List<String> lines) {
        return new Message(lines.stream().map(MovingPoint::of).collect(Collectors.toList()));
    }

    public void move() {
        movingPoints.forEach(MovingPoint::move);
        decryptTime++;
    }

    public char[][] decrypt() {
        decryptTime = 0;

        // The text is probably smaller than a page, so keep moving until the message size is reasonable
        while (size().count() > 80 * 40)
            move();

        var prev = grid();
        move();
        var next = grid();

        while (smaller(next, prev)) {
            prev = next;
            move();
            next = grid();
        }

        decryptTime--;
        return prev;
    }

    public int decryptTime() {
        return decryptTime;
    }

    private boolean smaller(char[][] next, char[][] prev) {
        return next.length * next[0].length < prev.length * prev[0].length;
    }

    public Size size() {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        for (var point : movingPoints) {
            minX = Math.min(minX, point.position().x());
            maxX = Math.max(maxX, point.position().x());
            minY = Math.min(minY, point.position().y());
            maxY = Math.max(maxY, point.position().y());
        }

        final var sx = Math.abs(minX - maxX);
        final var sy = Math.abs(minY - maxY);

        return new Size(sx + 1, sy + 1);
    }

    public char[][] grid() {
        final var size = size();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;

        for (var point : movingPoints) {
            minX = Math.min(minX, point.position().x());
            minY = Math.min(minY, point.position().y());
        }

        final var grid = new char[size.dy()][size.dx()];
        for (var row : grid)
            Arrays.fill(row, '.');

        for (var point : movingPoints)
            grid[point.position().y() - minY][point.position().x() - minX] = '#';

        return grid;
    }
}
