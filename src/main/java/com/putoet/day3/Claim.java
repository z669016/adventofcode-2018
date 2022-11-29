package com.putoet.day3;

import com.putoet.grid.Point;
import com.putoet.grid.Size;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Claim(int id, Point at, Size size) {
    // Patter to match: "#3 @ 5,5: 2x2"
    private static final Pattern PATTERN = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

    public static Claim of(String claim) {
        assert claim != null;

        final Matcher matcher = PATTERN.matcher(claim);
        if (!matcher.matches())
            throw new AssertionError("Invalid Claim input format '" + claim + "'");

        final int id = Integer.parseInt(matcher.group(1));
        final Point at = Point.of(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
        final Size size = new Size(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)));

        return new Claim(id, at, size);
    }

    @Override
    public String toString() {
        return String.format("#%d @ %d,%d: %s", id, at.x(), at.y(), size);
    }
}
