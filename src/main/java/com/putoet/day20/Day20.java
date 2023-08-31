package com.putoet.day20;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day20 {
    public static void main(String[] args) {
        final var finder = new RoomFinder(GridFactory.of(ResourceLines.line("/day20.txt")));

        Timer.run(() -> part1(finder));
        Timer.run(() -> part2(finder));
    }

    static void part1(RoomFinder finder) {
        final var furthest = finder.furthestRoom().orElseThrow();
        System.out.println("Furthest room route passes " + furthest + " doors.");
    }

    static void part2(RoomFinder finder) {
        System.out.println("Rooms with a minimal distance of 1000 is " + finder.distantRooms(1000));
    }
}
