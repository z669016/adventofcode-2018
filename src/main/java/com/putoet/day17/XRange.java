package com.putoet.day17;

import utilities.Grid;

public class XRange extends Range {

    public XRange(int fixed, int min, int max) {
        super(fixed, min, max);
    }

    @Override
    public void apply(Grid grid) {
        for (int idx = min; idx <= max; idx++)
            grid.set(idx, fixed, '#');
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
