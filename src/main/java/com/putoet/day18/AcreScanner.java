package com.putoet.day18;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class AcreScanner {
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

    public static Map<Point, AcreScan> scan(Grid grid) {
        final var scan = new HashMap<Point, AcreScan>();
        for (var y = grid.minY(); y < grid.maxY(); y++) {
            for (var x = grid.minX(); x < grid.maxX(); x++) {
                final var point = Point.of(x, y);
                scan.put(point, scanPoint(point, grid));
            }
        }

        return scan;
    }

    static AcreScan scanPoint(Point point, Grid grid) {
        final var adjacentPoints = adjacendPoints(point, grid);
        var adjacentOpen = 0;
        var adjacentTrees = 0;
        var adjacentLumberyards = 0;

        for (var adjacent : adjacentPoints) {
            switch (grid.get(adjacent.x(), adjacent.y())) {
                case GridFactory.LUMBERYARD -> adjacentLumberyards++;
                case GridFactory.OPEN_GROUD -> adjacentOpen++;
                case GridFactory.TREES -> adjacentTrees++;
                default -> throw new IllegalStateException("Invalid acre at " + adjacent);
            }
        }

        return new AcreScan(point, adjacentOpen, adjacentTrees, adjacentLumberyards);
    }

    static List<Point> adjacendPoints(Point point, Grid grid) {
        return ADJACENT.stream()
                .map(point::add)
                .filter(p -> grid.contains(p.x(), p.y()))
                .collect(Collectors.toList());
    }
}
