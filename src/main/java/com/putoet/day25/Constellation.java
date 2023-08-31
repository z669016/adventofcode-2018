package com.putoet.day25;

import java.util.HashSet;
import java.util.Set;

class Constellation {
    private final Set<Point4D> points = new HashSet<>();

    public Constellation add(Point4D point) {
        points.add(point);
        return this;
    }

    public void merge(Constellation other) {
        points.addAll(other.points);
    }

    public boolean contains(Point4D point) {
        return points.stream().anyMatch(p -> p.manhattanDistance(point) <= 3);
    }
}
