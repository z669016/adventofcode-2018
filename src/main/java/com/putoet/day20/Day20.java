package com.putoet.day20;

import com.putoet.grid.Grid;
import com.putoet.resources.ResourceLines;

import java.util.OptionalInt;

public class Day20 {
    public static void main(String[] args) {
        final Grid grid = GridFactory.of(ResourceLines.line("/day20.txt"));
        final RoomFinder finder = new RoomFinder(grid);

        part1(finder);
        part2(finder);
    }

    public static void part1(RoomFinder finder) {
        final OptionalInt furthest = finder.furthestRoom();
        if (furthest.isEmpty())
            System.out.println("No furthest room found");
        else
            System.out.println("Furthest room route passes " + furthest.getAsInt() + " doors.");
    }

    private static void part2(RoomFinder finder) {
        System.out.println("Rooms with a minimal distance of 1000 is " + finder.distantRooms(1000));
    }
}
