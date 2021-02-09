package com.putoet.day20;

import com.putoet.graph.UnweightedGraph;
import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Rooms {
    private static final char WALL = '#';
    private static final char UNEVEN_DOOR = '|';
    private static final char EVEN_DOOR = '-';
    private static final char ROOM = '.';
    private final UnweightedGraph<Point> graph = new UnweightedGraph<>();

    private Rooms() {
    }

    public static Rooms of(List<Route> routes) {
        assert routes != null;

        final Rooms rooms = new Rooms();
        routes.forEach(rooms::addRooms);

        return rooms;
    }

    public Point addRoom(Point point) {
        assert point != null;

        if (graph.contains(point))
            point = graph.vertexAt(graph.indexOf(point));
        else
            graph.addVertex(point);
        return point;
    }

    public void addRooms(Route route) {
        assert route != null;

        if (route.size() > 0) {
            final List<Point> points = route.asPoints();
            Point prev = addRoom(points.get(0));
            for (int idx = 1; idx < points.size(); idx++) {
                final Point next = addRoom(points.get(idx));
                graph.addEdge(prev, next);
                prev = next;
            }
        }
    }

    public Set<Point> asSet() {
        final HashSet<Point> rooms = new HashSet<>();
        for (int idx = 0; idx < graph.vertexCount(); idx++)
            rooms.add(graph.vertexAt(idx));

        return rooms;
    }

    public List<Point> nextTo(Point point) {
        assert point != null;
        if (!graph.contains(point))
            throw new IllegalArgumentException("Point " + point + " is not part f the graph.");

        return graph.neighboursOf(point);
    }

    public int size() {
        return graph.vertexCount();
    }

    public int doors() {
        return graph.edgeCount() / (size() - 1);
    }

    public char[][] asGrid() {
        final Set<Point> rooms = asSet();
        final int minX = rooms.stream().mapToInt(point -> point.x * 2).min().getAsInt() - 1;
        final int maxX = rooms.stream().mapToInt(point -> point.x * 2).max().getAsInt() + 2;
        final int minY = rooms.stream().mapToInt(point -> point.y * 2).min().getAsInt() - 1;
        final int maxY = rooms.stream().mapToInt(point -> point.y * 2).max().getAsInt() + 2;

        Function<Point, Point> adjust = (Point point) -> {
            final int adjustedX = point.x * 2;
            final int adjustedY = point.y * 2;
            return Point.of(adjustedX, adjustedY);
        };

        final Grid grid = new Grid(minX, maxX, minY, maxY, GridUtils.of(minX, maxX, minY, maxY, WALL));
        rooms.forEach(point -> {
            final Point adjustedPoint = adjust.apply(point);
            grid.set(adjustedPoint.x, adjustedPoint.y, ROOM);
            final List<Point> nextTo = nextTo(point);
            nextTo.forEach(next -> {
                if (next.y == point.y)
                    grid.set(adjustedPoint.x + (next.x < point.x ? -1 : 1), adjustedPoint.y, UNEVEN_DOOR);
                if (next.x == point.x)
                    grid.set(adjustedPoint.x,adjustedPoint.y + (next.y < point.y ? -1 : 1), EVEN_DOOR);
            });
        });
        grid.set(Point.ORIGIN.x, Point.ORIGIN.y, 'X');
        return grid.grid();
    }

    public static void print(Rooms rooms) {
        final char[][] grid = rooms.asGrid();

        for (int y = grid.length - 1; y >= 0; y--) {
            System.out.println(grid[y]);
        }
        System.out.println();
    }
}
