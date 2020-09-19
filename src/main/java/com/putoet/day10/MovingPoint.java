package com.putoet.day10;

import utilities.Point;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovingPoint {
    private Point point;
    private final Point velocity;

    private MovingPoint(Point point, Point velocity) {
        assert point != null && velocity != null;

        this.point = point;
        this.velocity = velocity;
    }

    public Point position() { return point; }
    public void move() {
        point = point.add(velocity);
    }

    // position=<-40409, -50575> velocity=< 4,  5>
    private static final Pattern pattern = Pattern.compile("position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>");

    public static MovingPoint of(String line) {
        final Matcher matcher = pattern.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid point definition '" + line + "'");

        final int x = Integer.parseInt(matcher.group(1).trim());
        final int y = Integer.parseInt(matcher.group(2).trim());
        final int vx = Integer.parseInt(matcher.group(3).trim());
        final int vy = Integer.parseInt(matcher.group(4).trim());

        return new MovingPoint(Point.of(x, y), Point.of(vx, vy));
    }
}
