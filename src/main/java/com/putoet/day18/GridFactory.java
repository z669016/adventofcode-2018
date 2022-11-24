package com.putoet.day18;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;

import java.util.List;
import java.util.Map;

public class GridFactory {
    public static final char OPEN_GROUD = '.';
    public static final char TREES = '|';
    public static final char LUMBERYARD = '#';

    public static Grid of(List<String> lines) {
        return new Grid(GridUtils.of(lines));
    }

    public static Grid next(Grid grid) {
        final Grid next = grid.copy();

        final Map<Point, AcreScan> acreScans = AcreScanner.scan(grid);
        acreScans.forEach((point, acreScan) -> next.set(point.x(), point.y(), next(acreScan, grid.get(point.x(), point.y()))));

        return next;
    }

    protected static char next(AcreScan acreScan, char element) {
        return switch (element) {
            case OPEN_GROUD -> acreScan.adjacentTrees >= 3? TREES : OPEN_GROUD;
            case TREES -> acreScan.adjacentLumberyards >= 3 ? LUMBERYARD : TREES;
            case LUMBERYARD -> acreScan.adjacentLumberyards > 0 && acreScan.adjacentTrees > 0 ? LUMBERYARD : OPEN_GROUD;
            default -> throw new IllegalStateException("Invalid element '" + element + "'");
        };
    }

    public static long totalResourceValue(Grid grid) {
        final long trees = GridUtils.count(grid.grid(), GridFactory.TREES);
        final long lumberyards = GridUtils.count(grid.grid(), GridFactory.LUMBERYARD);

        return trees * lumberyards;
    }
}
