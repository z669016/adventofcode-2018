package com.putoet.day22;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class CaveFactory {
    public static Region[][] of(int depth, @NotNull Point target) {
        assert depth > 0;
        assert target.x() >= 0 && target.x() < depth;
        assert target.y() >= 0 && target.y() < depth;

        final var grid = new Region[depth][depth];
        final var calculator = new Calculator(grid);

        for (var y = 0; y < depth; y++) {
            for (var x = 0; x < depth; x++) {
                grid[y][x] = new Region(Point.of(x, y), x == target.x() && y == target.y(), calculator);
            }
        }

        return grid;
    }

    public static List<String> dump(Region[][] grid, int depth) {
        final var dump = new ArrayList<String>();

        for (var y = 0; y < depth; y++) {
            final var sb = new StringBuilder();
            for (var x = 0; x < depth; x++) {
                sb.append(grid[y][x].symbol());
            }
            dump.add(sb.toString());
        }

        return dump;
    }
}
