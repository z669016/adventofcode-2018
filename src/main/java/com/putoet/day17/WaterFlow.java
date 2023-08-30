package com.putoet.day17;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Stack;

class WaterFlow {
    public static final char FLOWING_WATER = '|';
    public static final char STILL_WATER = '~';
    public static final char OPEN = '.';
    public static final char CLAY = '#';

    public static void flow(@NotNull Grid grid) {
        final var x = GirdFactory.WATER_X;
        final var y = grid.minY() + 1;
        grid.set(x, y, FLOWING_WATER);

        final var stack = new Stack<Point>();
        stack.push(Point.of(x, y));

        while (!stack.empty()) {
            final var point = stack.pop();
            if (grid.get(point.x(), point.y()) != FLOWING_WATER)
                continue;

            final var next = point.add(Point.of(0, 1));
            final var left = Optional.ofNullable(x >= grid.minX() ? point.add(Point.of(-1, 0)) : null);
            final var right = Optional.ofNullable(x < grid.maxX() ? point.add(Point.of(1, 0)) : null);

            // Don;t fall off the grid
            if (next.y() >= grid.maxY())
                continue;

            // if flowing watter can go down it will
            if (grid.get(next.x(), next.y()) == OPEN) {
                stack.push(point);
                grid.set(next.x(), next.y(), FLOWING_WATER);
                stack.push(next);
                continue;
            }

            // if flowing watter hits clay, move left (if possible) and move right (if possible)
            if (grid.get(next.x(), next.y()) == CLAY || grid.get(next.x(), next.y()) == STILL_WATER) {
                boolean pushed = false;

                if (left.isPresent() && grid.get(left.get().x(), left.get().y()) == OPEN) {
                    stack.push(point);
                    pushed = true;

                    grid.set(left.get().x(), left.get().y(), FLOWING_WATER);
                    stack.push(left.get());
                }

                if (right.isPresent() && grid.get(right.get().x(), right.get().y()) == OPEN) {
                    if (!pushed) {
                        stack.push(point);
                        pushed = true;
                    }

                    grid.set(right.get().x(), right.get().y(), FLOWING_WATER);
                    stack.push(right.get());
                }
                if (pushed)
                    continue;
            }

            // if below has been visited already, check if it should be converted into still water and go back
            if (grid.get(next.x(), next.y()) == FLOWING_WATER) {
                if (checkStillWater(next.x(), next.y(), grid))
                    stack.push(point);
            }
        }
    }

    private static boolean checkStillWater(int x, int y, Grid grid) {
        if (isInbetweenClay(x, y, grid)) {
            setStillWater(x, y, grid);
            return true;
        }

        return false;
    }

     @SuppressWarnings("StatementWithEmptyBody")
     private static boolean isInbetweenClay(int x, int y, Grid grid) {
        var xd = x;
        while (--xd > grid.minX() && grid.get(xd, y) == FLOWING_WATER) {}
        if (xd <= grid.minX() || grid.get(xd, y) != CLAY)
            return false;

        xd = x;
        while (++xd < grid.maxX() && grid.get(xd, y) == FLOWING_WATER) {}
        return xd < grid.maxX() && grid.get(xd, y) == CLAY;
     }

    private static void setStillWater(int x, int y, Grid grid) {
        grid.set(x, y, STILL_WATER);

        int xd = x;
        while (--xd > grid.minX() && grid.get(xd, y) == FLOWING_WATER)
            grid.set(xd, y, STILL_WATER);

        xd = x;
        while (++xd > grid.minX() && grid.get(xd, y) == FLOWING_WATER)
            grid.set(xd, y, STILL_WATER);
    }

    public static long tilesReached(Grid grid) {
        return GridUtils.count(grid.grid(), FLOWING_WATER) + GridUtils.count(grid.grid(), STILL_WATER);
    }

    public static long tilesStillWater(Grid grid) {
        return GridUtils.count(grid.grid(), STILL_WATER);
    }
}
