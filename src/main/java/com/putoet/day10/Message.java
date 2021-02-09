package com.putoet.day10;

import com.putoet.grid.GridUtils;
import com.putoet.utilities.Size;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Message {
    public static Message of(List<String> lines) {
        return new Message(lines.stream().map(MovingPoint::of).collect(Collectors.toList()));
    }

    private final List<MovingPoint> movingPoints;
    private int decryptTime = 0;

    private Message(List<MovingPoint> movingPoints) {
        assert movingPoints != null && movingPoints.size() > 0;

        this.movingPoints = movingPoints;
    }

    public void move() {
        movingPoints.forEach(MovingPoint::move);
        decryptTime++;
    }

    public char[][] decrypt() {
        decryptTime = 0;

        // The text is probably smaller then a page, so keep moving until the message size is reasonable
        while (size().count() > 80*40)
            move();

        char[][] prev = grid();
        move();
        char[][] next = grid();

        while(smaller(next, prev)) {
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

    public char[][] print() {
        final char[][] grid = grid();
        GridUtils.print(grid);

        return grid;
    }

    public Size size() {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        for (MovingPoint point : movingPoints) {
            minX = Math.min(minX, point.position().x);
            maxX = Math.max(maxX, point.position().x);
            minY = Math.min(minY, point.position().y);
            maxY = Math.max(maxY, point.position().y);
        }

        final int sx = Math.abs(minX - maxX);
        final int sy = Math.abs(minY - maxY);

        return Size.of(sx+1, sy+1);
    }

    public char[][] grid() {
        final Size size = size();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;

        for (MovingPoint point : movingPoints) {
            minX = Math.min(minX, point.position().x);
            minY = Math.min(minY, point.position().y);
        }

        final char[][] grid = new char[size.dy][size.dx];
        for (char[] row : grid)
            Arrays.fill(row, '.');

        for (MovingPoint point : movingPoints)
            grid[point.position().y - minY][point.position().x - minX] = '#';

        return grid;
    }
}
