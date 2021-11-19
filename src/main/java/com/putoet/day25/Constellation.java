package com.putoet.day25;

import java.util.HashSet;
import java.util.Set;

public class Constellation {
    private final Set<Point4D> points = new HashSet<>();

    public Constellation add(Point4D point) {
        points.add(point);
        return this;
    }

    public Constellation merge(Constellation other) {
        points.addAll(other.points);
        return this;
    }

    public boolean contains(Point4D point) {
        return points.stream().anyMatch(p -> p.manhattanDistance(point) <= 3);
    }
}
