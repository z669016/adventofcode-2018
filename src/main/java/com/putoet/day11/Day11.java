package com.putoet.day11;

import com.putoet.grid.Point;

public class Day11 {
    public static void main(String[] args) {
        final int input = 8199;
        final FuelGrid grid = new FuelGrid(input);

        part1(grid);
        part2(grid);
    }

    private static void part1(FuelGrid grid) {
        System.out.println("Coordinate for max 3x3 power level is " + grid.maxThreeByThree());
    }

    private static void part2(FuelGrid grid) {
        int max = Integer.MIN_VALUE;
        int maxSize = 0;
        Point maxPoint = Point.of(1, 1);
        for (int size = 3; size <= 300; size++) {
            final Point point = grid.maxXByX(size);
            final int sum = grid.xByXSum(point, size);

            if (sum > max) {
                maxSize = size;
                maxPoint = point;
                max = sum;
            }
        }

        System.out.println("Coordinate for max power level of any size is " + maxPoint + " for size " + maxSize);
    }
}
