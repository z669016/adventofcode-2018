package utilities;

import java.util.Arrays;

public class Grid {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final char[][] grid;

    public Grid(char[][] grid) {
        this(0, grid[0].length, 0, grid.length, grid);
    }

    public Grid(int minX, int maxX, int minY, int maxY, char[][] grid) {
        assert grid != null;
        assert grid.length == maxY - minY;
        for (char[] row : grid)
            assert row.length == maxX - minX;

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.grid = grid;
    }

    public Grid copy() {
        final char[][] copy = new char[maxY - minY][maxX - minX];
        for (int y = 0; y < grid.length; y++)
            System.arraycopy(grid[y], 0, copy[y], 0, grid[y].length);

        return new Grid(minX, maxX, minY, maxY, copy);
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
    public char[][] grid() { return grid; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("(%d,%d)..(%d,%d)%n", minX, minY, maxX, maxY));
        GridUtils.toList(grid).forEach(line -> sb.append(line).append("\n"));

        return sb.toString();
    }
}
