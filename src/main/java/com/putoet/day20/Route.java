package com.putoet.day20;

import com.putoet.utilities.Point;

import java.util.ArrayList;
import java.util.List;

public class Route {
    public static final Point NORTH = Point.of(0, 1);
    public static final Point EAST = Point.of(1, 0);
    public static final Point SOUTH = Point.of(0, -1);
    public static final Point WEST = Point.of(-1, 0);

    private final String route;

    public Route(String route) {
        assert route != null;
        if (!route.matches("[NESW]+"))
            throw new AssertionError(("Invalid route'" + route + "'"));

        this.route = route;
    }

    public List<Point> asPoints() {
        final List<Point> points = new ArrayList<>();

        Point point = Point.ORIGIN;
        points.add(point);
        for (int offset = 0; offset < route.length(); offset++) {
            point = point.add(switch (route.charAt(offset)) {
                case 'N' -> NORTH;
                case 'W' -> WEST;
                case 'S' -> SOUTH;
                case 'E' -> EAST;
                default -> throw new IllegalArgumentException("Invalid character in route '" + route + "'");
            });
            points.add(point);
        }

        return points;
    }

    public int size() {
        return route.length();
    }

    @Override
    public String toString() {
        return route;
    }
}
