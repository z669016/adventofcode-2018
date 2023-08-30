package com.putoet.day6;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day6 {
    public static void main(String[] args) {
        final var grid = Grid.of(PointFactory.of(ResourceLines.list("/day6.txt")));

        Timer.run(() -> part1(grid));
        Timer.run(() -> part2(grid));
    }

    private static void part1(Grid grid) {
        grid.fillClosest();

        final var enclosed = grid.enclosedLetters();
        final var max = enclosed.stream().mapToLong(grid::count).max().orElseThrow();

        System.out.println("The largest area is " + max);
    }

    private static void part2(Grid grid) {
        grid.fill();
        System.out.println("The area with max distance is 10.000 is " + grid.countMax(10_000));
    }
}
