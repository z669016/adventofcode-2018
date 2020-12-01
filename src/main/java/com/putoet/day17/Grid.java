package com.putoet.day17;

import utilities.GridUtils;

public class Grid {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final char[][] grid;

    public Grid(int minX, int maxX, int minY, int maxY, char[][] grid) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.grid = grid;
    }

    public void set(int x, int y, char c) {
        assert x >= minX && x < maxX;
        assert y >= minY && y < maxY;

        grid[y - minY][x - minX] = c;
    }

    public char get(int x, int y) {
        assert x >= minX && x < maxX;
        assert y >= minY && y < maxY;

        return grid[y - minY][x - minX];
    }

    public int minX() { return minX; }
    public int maxX() { return maxX; }
    public int minY() { return minY; }
    public int maxY() { return maxY; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("(%d,%d)..(%d,%d)%n", minX, minY, maxX, maxY));
        GridUtils.toList(grid).forEach(line -> sb.append(line).append("\n"));

        return sb.toString();
    }
}
