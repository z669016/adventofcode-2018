package com.putoet.day17;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;

import java.util.List;
import java.util.stream.Collectors;

public class GirdFactory {
    public static final int WATER_X = 500;
    public static final int WATER_Y = 0;

    public static Grid of(List<String> lines) {
        assert lines != null;

        final List<Range> ranges = lines.stream().map(Range::of).collect(Collectors.toList());
        final int minX = ranges.stream().mapToInt(Range::minX).min().orElse(0) - 1;
        final int maxX = ranges.stream().mapToInt(Range::maxX).max().orElse(0) + 2;
        final int minY = ranges.stream().mapToInt(Range::minY).min().orElse(0) - 1;
        final int maxY = ranges.stream().mapToInt(Range::maxY).max().orElse(0) + 1;

        final Grid grid = new Grid(minX, maxX, minY, maxY, GridUtils.of(minX, maxX, minY, maxY, '.'));
        ranges.forEach(range -> range.apply(grid));

        grid.set(WATER_X, minY, '+');
        return grid;
    }
}
