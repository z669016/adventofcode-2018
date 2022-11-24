package com.putoet.day22;

import com.putoet.grid.Point;

import java.util.ArrayList;
import java.util.List;

public class CaveFactory {
    public static Region[][] of(int depth, Point target) {
        assert depth > 0;
        assert target.x() >= 0 && target.x() < depth;
        assert target.y() >= 0 && target.y() < depth;

        final Region[][] grid = new Region[depth][depth];
        final Calculator calculator = new Calculator(grid);

        for (int y = 0; y < depth; y++) {
            for (int x = 0; x < depth; x++) {
                grid[y][x] = new Region(Point.of(x, y), x == target.x() && y == target.y(), calculator);
            }
        }

        return grid;
    }

    public static List<String> dump(Region[][] grid, int depth) {
        final List<String> dump = new ArrayList<>();

        for (int y = 0; y < depth; y++) {
            final StringBuilder sb = new StringBuilder();
            for (int x = 0; x < depth; x++) {
                sb.append(grid[y][x].symbol());
            }
            dump.add(sb.toString());
        }

        return dump;
    }
}
