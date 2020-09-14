package com.putoet.day3;

import utilities.Point;
import utilities.Size;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Claim {
    // Patter to match: "#3 @ 5,5: 2x2"
    private static final Pattern PATTERN = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

    private final int id;
    private final Point at;
    private final Size size;

    public static Claim of(String claim) {
        assert claim != null;

        final Matcher matcher = PATTERN.matcher(claim);
        if (!matcher.matches())
            throw new AssertionError("Invalid Claim input format '" + claim + "'");

        int id = Integer.parseInt(matcher.group(1));
        Point at = Point.of(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
        Size size = Size.of(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)));

        return new Claim(id, at, size);
    }

    private Claim(int id, Point at, Size size) {
        this.id = id;
        this.at = at;
        this.size = size;
    }

    public int id() { return id; }
    public Point at() { return at; }
    public Size size() { return size; }

    @Override
    public String toString() {
        return String.format("#%d @ %d,%d: %s", id, at.x, at.y, size);
    }
}
