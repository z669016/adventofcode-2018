package com.putoet.day17;

import com.putoet.utilities.Grid;

public class YRange extends Range {
    public YRange(int fixed, int min, int max) {
        super(fixed, min, max);
    }

    @Override
    public void apply(Grid grid) {
        for (int idy = min; idy <= max; idy++)
            grid.set(fixed, idy, '#');

    }

    @Override
    public int minX() {
        return fixed;
    }

    @Override
    public int maxX() {
        return fixed;
    }

    @Override
    public int minY() {
        return min;
    }

    @Override
    public int maxY() {
        return max;
    }

    @Override
    public String toString() {
        return String.format("x=%d, y=%d..%d", fixed, min, max);
    }
}
