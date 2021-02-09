package com.putoet.day20;

import com.putoet.resources.ResourceLines;
import com.putoet.search.GenericSearch;
import com.putoet.utilities.Point;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

public class Day20 {
    public static void main(String[] args) {
        final Rooms rooms = Rooms.of(Routes.of(ResourceLines.line("/day20.txt")));
        Rooms.print(rooms);

        final OptionalInt max = part1(rooms);
        if (max.isPresent())
            System.out.println("Longest route passed " + max.getAsInt() + " doors.");
        else
            System.out.println("No longest route...");
    }

    protected static OptionalInt part1(Rooms rooms) {
        final Set<Point> roomLocations = rooms.asSet();
        return roomLocations.stream()
                .map(point -> shortestPathTo(rooms, point))
                .filter(OptionalInt::isPresent)
                .mapToInt(OptionalInt::getAsInt)
                .max();

    }

    protected static OptionalInt shortestPathTo(Rooms rooms, Point to) {
        final Optional<GenericSearch.Node<Point>> bfs = GenericSearch.bfs(Point.ORIGIN, v -> v.equals(to), rooms::nextTo);
        return bfs.isEmpty() ? OptionalInt.empty() : OptionalInt.of(bfs.get().steps());
    }
}
