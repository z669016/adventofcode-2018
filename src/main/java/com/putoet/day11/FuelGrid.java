package com.putoet.day11;

import com.putoet.grid.Point;
import com.putoet.grid.Size;
import org.jetbrains.annotations.NotNull;

class FuelGrid {
    private final Size size = new Size(300, 300);
    private final int[][] grid = new int[size.dy()][size.dx()];

    public FuelGrid(int serialNumber) {
        setupGrid(serialNumber, grid);
    }

    private static void setupGrid(int serialNumber, int[][] grid) {
        for (var y = 0; y < grid.length; y++)
            for (var x = 0; x < grid.length; x++)
                grid[y][x] = powerLevel(serialNumber, Point.of(x + 1, y + 1));
    }

    public static int powerLevel(int serialNumber, @NotNull Point point) {
        final var rackId = rackId(point);

        var powerLevel = rackId * point.y();
        powerLevel += serialNumber;
        powerLevel *= rackId;
        powerLevel = hundreds(powerLevel);
        powerLevel -= 5;

        return powerLevel;
    }

    public static int rackId(@NotNull Point point) {
        return point.x() + 10;
    }

    public static int hundreds(int value) {
        if (value < 100)
            return 0;

        if (value < 1000)
            return value / 100;

        return (value % 1000) / 100;
    }

    public int get(@NotNull Point point) {
        assert point.x() > 0 && point.x() <= size.dx();
        assert point.y() > 0 && point.y() <= size.dy();

        return grid[point.y() - 1][point.x() - 1];
    }

    public int threeByThreeSum(@NotNull Point point) {
        return xByXSum(point, 3);
    }

    public int xByXSum(@NotNull Point point, int size) {
        assert point.x() > 0 && point.x() <= this.size.dx() - size + 1;
        assert point.y() > 0 && point.y() <= this.size.dy() - size + 1;

        final int x = point.x() - 1, y = point.y() - 1;
        int sum = 0;
        for (int dy = 0; dy < size; dy++)
            for (int dx = 0; dx < size; dx++)
                sum += grid[y + dy][x + dx];

        return sum;
    }

    public Point maxThreeByThree() {
        return maxXByX(3);
    }

    public Point maxXByX(int size) {
        var maxPoint = Point.of(1, 1);
        int max = Integer.MIN_VALUE;

        for (int y = 0; y < grid.length - size + 1; y++)
            for (int x = 0; x < grid.length - size + 1; x++) {
                final var point = Point.of(x + 1, y + 1);
                int gridPower = xByXSum(point, size);
                if (gridPower > max) {
                    max = gridPower;
                    maxPoint = point;
                }
            }

        return maxPoint;
    }
}
