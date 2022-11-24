package com.putoet.day20;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;
import com.putoet.search.GenericSearch;

import java.util.*;
import java.util.stream.Collectors;

public class RoomFinder {
    private final Grid grid;
    private final Point start;

    public RoomFinder(Grid grid) {
        this.grid = grid;

        final Optional<Point> x = grid.findFirst(c -> c == 'X');
        assert x.isPresent();

        this.start = x.get();
    }

    protected Optional<GenericSearch.Node<Point>> routeTo(Room room) {
        return GenericSearch.bfs(start, p->p.equals(room.location), p -> {
            final List<Point> nextPoints = new ArrayList<>();
            for (Point direction : Point.directions(true)) {
                Point next = p.add(direction);
                if (grid.contains(next.x(), next.y()) && Door.isDoor(grid.get(next.x(), next.y()))) {
                    next = next.add(direction);
                    if (!Room.isRoom(grid.get(next.x(), next.y())))
                        throw new IllegalStateException("Looking for " + room + "  entering invalid location " + next);

                    nextPoints.add(next);
                }
            }
            return nextPoints;
        });
    }

    public OptionalInt furthestRoom() {
        return doorsTo(rooms()).stream().mapToInt(i -> i).max();
    }

    public long distantRooms(int distance) {
        return doorsTo(rooms()).stream().filter(i ->i >= distance).count();
    }

    public List<Integer> doorsTo(List<Room> rooms) {
        return rooms.stream()
                .map(this::doorsTo)
                .filter(OptionalInt::isPresent)
                .map(OptionalInt::getAsInt)
                .collect(Collectors.toList());
    }

    public OptionalInt doorsTo(Room room) {
        final Optional<GenericSearch.Node<Point>> route = routeTo(room);
        if (route.isEmpty())
            return OptionalInt.empty();
        return OptionalInt.of(route.get().steps());
    }

    public List<Room> rooms() {
        return grid.findAll(c -> c == Room.SYMBOL).stream()
                .map(Room::new)
                .collect(Collectors.toList());
    }
}
