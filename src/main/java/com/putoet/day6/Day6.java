package com.putoet.day6;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.OptionalLong;

public class Day6 {
    public static void main(String[] args) {
        final List<Point> points = PointFactory.of(ResourceLines.list("/day6.txt"));
        final Grid grid = Grid.of(points);

        part1(grid);
        part2(grid);
    }

    private static void part1(Grid grid) {
        grid.fillClosest();

        final List<Character> enclosed = grid.enclosedLetters();
        final OptionalLong max = enclosed.stream().mapToLong(grid::count).max();
        if (max.isEmpty()) {
            System.out.println("No largest area found ...");
            return;
        }

        System.out.println("The largest area is " + max.getAsLong());
    }

    private static void part2(Grid grid) {
        grid.fill();
        System.out.println("The area with max distance is 10.000 is " + grid.countMax(10_000));
    }
}
