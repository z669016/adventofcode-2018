package com.putoet.day11;

import com.putoet.grid.Point;
import com.putoet.utils.Timer;

public class Day11 {
    public static void main(String[] args) {
        final var grid = new FuelGrid(8199);

        Timer.run(() -> part1(grid));
        Timer.run(() -> part2(grid));
    }

    private static void part1(FuelGrid grid) {
        System.out.println("Coordinate for max 3x3 power level is " + grid.maxThreeByThree());
    }

    private static void part2(FuelGrid grid) {
        var maxSum = Integer.MIN_VALUE;
        var maxSize = 0;
        var maxPoint = Point.of(1, 1);

        for (int size = 3; size <= 300; size++) {
            final var point = grid.maxXByX(size);
            final var sum = grid.xByXSum(point, size);

            if (sum > maxSum) {
                maxSize = size;
                maxPoint = point;
                maxSum = sum;
            }
        }

        System.out.println("Coordinate for max power level of any size is " + maxPoint + " for size " + maxSize);
    }
}
