package com.putoet.day11;

import com.putoet.grid.Point;
import com.putoet.utilities.Size;

public class FuelGrid {
    private final Size size = Size.of(300, 300);
    private final int serialNumber;
    private final int[][] grid = new int[size.dy][size.dx];

    public FuelGrid(int serialNumber) {
        this.serialNumber = serialNumber;
        setupGrid(serialNumber, grid);
    }

    public int get(Point point) {
        assert point != null;
        assert point.x > 0 && point.x <= size.dx;
        assert point.y > 0 && point.y <= size.dy;

        return grid[point.y-1][point.x-1];
    }

    public int threeByThreeSum(Point point) {
        return xByXSum(point, 3);
    }

    public int xByXSum(Point point, int size) {
        assert point != null;
        assert point.x > 0 && point.x <= this.size.dx - size + 1;
        assert point.y > 0 && point.y <= this.size.dy - size + 1;

        final int x = point.x - 1, y = point.y - 1;
        int sum = 0;
        for (int dy = 0; dy < size; dy++)
            for (int dx = 0; dx < size; dx++)
                sum += grid[y+dy][x+dx];

        return sum;
    }

    public Point maxThreeByThree() {
        return maxXByX(3);
    }

    public Point maxXByX(int size) {
        Point maxPoint = Point.of(1, 1);
        int max = Integer.MIN_VALUE;

        for (int y = 0; y < grid.length-size+1; y++)
            for (int x = 0; x < grid.length-size+1; x++) {
                final Point point = Point.of(x+1, y+1);
                int gridPower = xByXSum(point, size);
                if (gridPower > max) {
                    max = gridPower;
                    maxPoint = point;
                }
            }

        return maxPoint;
    }

    private static void setupGrid(int serialNumber, int[][] grid) {
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid.length; x++)
                grid[y][x] = powerLevel(serialNumber, Point.of(x + 1, y + 1));
    }

    public static int powerLevel(int serialNumber, Point point) {
        assert point != null;

        final int rackId = rackId(point);
        int powerLevel = rackId * point.y;
        powerLevel += serialNumber;
        powerLevel *= rackId;
        powerLevel = hundreds(powerLevel);
        powerLevel -= 5;

        return powerLevel;
    }

    public static int rackId(Point point) {
        assert point != null;

        return point.x + 10;
    }

    public static int hundreds(int value) {
        return (value % 1_000) / 100;
    }
}
