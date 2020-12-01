package com.putoet.day17;

import java.util.regex.Matcher;
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

    public abstract void apply(char[][] grid);
    public abstract int minX();
    public abstract int maxX();
    public abstract int minY();
    public abstract int maxY();

    private static final Pattern PATTERN = Pattern.compile("([xy])=(\\d+), ([xy])=(\\d+)\\.\\.(\\d+)");

    public static Range of(String line) {
        assert line != null;

        final Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid range: " + line);
        else {
            final String first = matcher.group(1);
            final int fixed = Integer.parseInt(matcher.group(2));
            final String second = matcher.group(3);
            final int min = Integer.parseInt(matcher.group(4));
            final int max = Integer.parseInt(matcher.group(5));

            if (first.equals(second))
                throw new IllegalArgumentException("Invalid range: " + line);

            if (first.equals("x"))
                return new YRange(fixed, min, max);
            else
                return new XRange(fixed, min, max);
        }
    }
}
