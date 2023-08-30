package com.putoet.day6;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;

class PointFactory {
    public static List<Point> of(@NotNull List<String> csvs) {
        return csvs.stream()
            .map(PointFactory::of)
            .toList();
    }

    public static Point of(@NotNull String csv) {
        final var xy = csv.split(", ");
        assert xy.length == 2;

        return Point.of(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
    }
}
