package com.putoet.day18;

import utilities.Grid;
import utilities.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcreScanner {

    public static final Point NORTH = Point.of(0, -1);
    public static final Point NORTH_WEST = Point.of(1, -1);
    public static final Point WEST = Point.of(1, 0);
    public static final Point SOUTH_WEST = Point.of(1, 1);
    public static final Point SOUTH = Point.of(0, 1);
    public static final Point SOUTH_EAST = Point.of(-1, 1);
    public static final Point EAST = Point.of(-1, 0);
    public static final Point NORTH_EAST = Point.of(-1, -1);

    public static Map<Point,AcreScan> scan(Grid grid) {
        final Map<Point,AcreScan> scan =new HashMap<>();
        for (int y =grid.minY();y < grid.maxY(); y++)
            for (int x = grid.minX(); x < grid.maxX(); x++) {
                final Point point = Point.of(x, y);
                scan.put(point, scanPoint(point, grid));
            }
        return scan;
    }

    protected static AcreScan scanPoint(Point point, Grid grid) {
        final List<Point> adjacentPoints = adjacendPoints(point, grid);
        int adjacentOpen = 0;
        int adjacentTrees = 0;
        int adjacentLumberyards = 0;

        for (Point adjacent : adjacentPoints) {
            switch (grid.get(adjacent.x, adjacent.y)) {
                case GridFactory.LUMBERYARD -> adjacentLumberyards++;
                case GridFactory.OPEN_GROUD -> adjacentOpen++;
                case GridFactory.TREES -> adjacentTrees++;
                default -> throw new IllegalStateException("Invalid acre at " + adjacent);
            }
        }

        return new AcreScan(point, adjacentOpen, adjacentTrees, adjacentLumberyards);
    }

    protected static List<Point> adjacendPoints(Point point, Grid grid) {
        final List<Point> list = new ArrayList<>();
        if (point.y > grid.minY()) list.add(point.add(NORTH));
        if (point.y > grid.minY() && point.x < grid.maxX() - 1) list.add(point.add(NORTH_WEST));
        if (point.x < grid.maxX() - 1) list.add(point.add(WEST));
        if (point.x < grid.maxX() - 1 && point.y < grid.maxY() - 1) list.add(point.add(SOUTH_WEST));
        if (point.y < grid.maxY() - 1) list.add(point.add(SOUTH));
        if (point.y < grid.maxY() - 1 && point.x > grid.minX()) list.add(point.add(SOUTH_EAST));
        if (point.x > grid.minX()) list.add(point.add(EAST));
        if (point.x > grid.minX() && point.y > grid.minY()) list.add(point.add(NORTH_EAST));

        return list;
    }
}
