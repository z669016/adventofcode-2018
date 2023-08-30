package com.putoet.day3;

import com.putoet.grid.Point;
import com.putoet.grid.Size;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

record Claim(int id, Point at, Size size) {
    // Patter to match: "#3 @ 5,5: 2x2"
    private static final Pattern PATTERN = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

    public static Claim of(@NotNull String claim) {
        final var matcher = PATTERN.matcher(claim);
        if (!matcher.matches())
            throw new AssertionError("Invalid Claim input format '" + claim + "'");

        final var id = Integer.parseInt(matcher.group(1));
        final var at = Point.of(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
        final var size = new Size(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)));

        return new Claim(id, at, size);
    }
}
