package com.putoet.day6;

import com.putoet.resources.ResourceLines;
import utilities.Point;
import utilities.PointFactory;

import java.util.List;
import java.util.OptionalLong;

public class Day6 {
    public static void main(String[] args) {
        final List<Point> points = PointFactory.of(ResourceLines.list("/day6.txt"));
        final Grid grid = Grid.of(points);

        partOne(grid);
        partTwo(grid);
    }

    private static void partTwo(Grid grid) {
        grid.fill();
        System.out.println("The area with max distance is 10.000 is " + grid.countMax(10_000));
    }

    private static void partOne(Grid grid) {
        grid.fillClosest();

        final List<Character> enclosed = grid.enclosedLetters();
        final OptionalLong max = enclosed.stream().mapToLong(grid::count).max();

        System.out.println("The largest area is " + max.getAsLong());
    }
}
