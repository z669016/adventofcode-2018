package com.putoet.day22;

import com.putoet.grid.Point;

import java.util.Optional;

public class Day22 {
    public static final int DEPTH = 5355;
    public static Point TARGET = Point.of(14,796);

    public static void main(String[] args) {
        final Region[][] grid = CaveFactory.of(DEPTH, TARGET);

        part1(grid);
        part2(grid);
    }

    private static void part1(Region[][] grid) {
        System.out.println("Risk level for the target area is " + targetAreaRiskLevel(grid, TARGET));
    }

    public static int targetAreaRiskLevel(Region[][] grid, Point target) {
        assert target.y() >= 0 && target.y() < grid.length;
        assert target.x() >= 0 && target.x() < grid[target.y()].length;
        assert grid[target.y()][target.x()].isTarget();

        int riskLevel = 0;
        for (int y = 0; y <= target.y();y++) {
            for (int x = 0;x <= target.x(); x++)
                riskLevel += grid[y][x].riskLevel();
        }

        return riskLevel;
    }

    private static void part2(Region[][] grid) {
        System.out.println("Minutes to target is " + rescue(grid, TARGET));
    }

    public static int rescue(Region[][] grid, Point target) {
        final Rescue rescue = new Rescue(grid, target);
        final Optional<Rescue.Node> node = rescue.rescue();

        if (node.isEmpty())
            throw new IllegalStateException("No path found to target region");

        return node.get().timer;
    }
}
