package com.putoet.day18;


import com.putoet.grid.Grid;
import com.putoet.grid.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AcreScanner {
    public static final Point NORTH = Point.of(0, -1);
    public static final Point NORTH_WEST = Point.of(1, -1);
    public static final Point WEST = Point.of(1, 0);
    public static final Point SOUTH_WEST = Point.of(1, 1);
    public static final Point SOUTH = Point.of(0, 1);
    public static final Point SOUTH_EAST = Point.of(-1, 1);
    public static final Point EAST = Point.of(-1, 0);
    public static final Point NORTH_EAST = Point.of(-1, -1);
    public static final List<Point> ADJACENT = List.of(
            NORTH,
            NORTH_WEST,
            WEST,
            SOUTH_WEST,
            SOUTH,
            SOUTH_EAST,
            EAST,
            NORTH_EAST
    );

    public static Map<Point,AcreScan> scan(Grid grid) {
        final Map<Point,AcreScan> scan = new HashMap<>();
        for (int y = grid.minY();y < grid.maxY(); y++) {
            for (int x = grid.minX(); x < grid.maxX(); x++) {
                final Point point = Point.of(x, y);
                scan.put(point, scanPoint(point, grid));
            }
        }

        return scan;
    }

    protected static AcreScan scanPoint(Point point, Grid grid) {
        final List<Point> adjacentPoints = adjacendPoints(point, grid);
        int adjacentOpen = 0;
        int adjacentTrees = 0;
        int adjacentLumberyards = 0;

        for (Point adjacent : adjacentPoints) {
            switch (grid.get(adjacent.x(), adjacent.y())) {
                case GridFactory.LUMBERYARD -> adjacentLumberyards++;
                case GridFactory.OPEN_GROUD -> adjacentOpen++;
                case GridFactory.TREES -> adjacentTrees++;
                default -> throw new IllegalStateException("Invalid acre at " + adjacent);
            }
        }

        return new AcreScan(point, adjacentOpen, adjacentTrees, adjacentLumberyards);
    }

    protected static List<Point> adjacendPoints(Point point, Grid grid) {
        return ADJACENT.stream()
                .map(point::add)
                .filter(p -> grid.contains(p.x(), p.y()))
                .collect(Collectors.toList());
    }
}
