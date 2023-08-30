package com.putoet.day17;

import com.putoet.grid.Grid;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public abstract class Range {
    protected final int fixed;
    protected final int min;
    protected final int max;

    public Range(int fixed, int min, int max) {
        this.fixed = fixed;
        this.min = min;
        this.max = max;
    }

    public abstract void apply(Grid grid);
    public abstract int minX();
    public abstract int maxX();
    public abstract int minY();
    public abstract int maxY();

    private static final Pattern PATTERN = Pattern.compile("([xy])=(\\d+), ([xy])=(\\d+)\\.\\.(\\d+)");

    public static Range of(@NotNull String line) {
        final var matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid range: " + line);
        else {
            final var first = matcher.group(1);
            final var fixed = Integer.parseInt(matcher.group(2));
            final var second = matcher.group(3);
            final var min = Integer.parseInt(matcher.group(4));
            final var max = Integer.parseInt(matcher.group(5));

            if (first.equals(second))
                throw new IllegalArgumentException("Invalid range: " + line);

            if (first.equals("x"))
                return new YRange(fixed, min, max);
            else
                return new XRange(fixed, min, max);
        }
    }
}
