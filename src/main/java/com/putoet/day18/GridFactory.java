package com.putoet.day18;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;

import java.util.List;

class GridFactory {
    public static final char OPEN_GROUD = '.';
    public static final char TREES = '|';
    public static final char LUMBERYARD = '#';

    public static Grid of(List<String> lines) {
        return new Grid(GridUtils.of(lines));
    }

    public static Grid next(Grid grid) {
        final var next = grid.copy();
        final var acreScans = AcreScanner.scan(grid);
        acreScans.forEach((point, acreScan) -> next.set(point.x(), point.y(), next(acreScan, grid.get(point.x(), point.y()))));

        return next;
    }

    static char next(AcreScan acreScan, char element) {
        return switch (element) {
            case OPEN_GROUD -> acreScan.adjacentTrees() >= 3 ? TREES : OPEN_GROUD;
            case TREES -> acreScan.adjacentLumberyards() >= 3 ? LUMBERYARD : TREES;
            case LUMBERYARD ->
                    acreScan.adjacentLumberyards() > 0 && acreScan.adjacentTrees() > 0 ? LUMBERYARD : OPEN_GROUD;
            default -> throw new IllegalStateException("Invalid element '" + element + "'");
        };
    }

    static long totalResourceValue(Grid grid) {
        final var trees = GridUtils.count(grid.grid(), GridFactory.TREES);
        final var lumberyards = GridUtils.count(grid.grid(), GridFactory.LUMBERYARD);

        return trees * lumberyards;
    }
}
