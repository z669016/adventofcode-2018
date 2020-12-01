package com.putoet.day17;

public class XRange extends Range {

    public XRange(int fixed, int min, int max) {
        super(fixed, min, max);
    }

    @Override
    public void apply(char[][] grid) {
        for (int idx = min; idx <= max; idx++)
            grid[fixed][idx] = '#';
    }

    @Override
    public int minX() {
        return min;
    }

    @Override
    public int maxX() {
        return max;
    }

    @Override
    public int minY() {
        return fixed;
    }

    @Override
    public int maxY() {
        return fixed;
    }

    @Override
    public String toString() {
        return String.format("y=%d, x=%d..%d", fixed, min, max);
    }
}
