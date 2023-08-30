package com.putoet.day10;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MovingPoint {
    // position=<-40409, -50575> velocity=< 4,  5>
    private static final Pattern pattern = Pattern.compile("position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>");
    private final Point velocity;
    private Point point;

    private MovingPoint(@NotNull Point point, @NotNull Point velocity) {
        this.point = point;
        this.velocity = velocity;
    }

    public static MovingPoint of(@NotNull String line) {
        final var matcher = pattern.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid point definition '" + line + "'");

        final int x = Integer.parseInt(matcher.group(1).trim());
        final int y = Integer.parseInt(matcher.group(2).trim());
        final int vx = Integer.parseInt(matcher.group(3).trim());
        final int vy = Integer.parseInt(matcher.group(4).trim());

        return new MovingPoint(Point.of(x, y), Point.of(vx, vy));
    }

    public Point position() {
        return point;
    }

    public void move() {
        point = point.add(velocity);
    }
}
