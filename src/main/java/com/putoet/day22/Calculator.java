package com.putoet.day22;

import org.jetbrains.annotations.NotNull;

class Calculator {
    private final Region[][] grid;

    public Calculator(Region[][] grid) {
        this.grid = grid;
    }

    public long geologicIndex(@NotNull Region region) {
        if (region.isMouth()) return 0;
        if (region.isTarget()) return 0;

        final int y = region.coordinate().y();
        final int x = region.coordinate().x();

        if (y == 0) return x * 16807L;
        if (x == 0) return y * 48271L;

        return grid[y][x-1].erosionLevel() * grid[y-1][x].erosionLevel();
    }

    public long caveSystemDepth() {
        return grid.length;
    }

    public long erosionLevel(@NotNull Region region) {
        return (region.geologicIndex() + caveSystemDepth()) %  20183;
    }
}
