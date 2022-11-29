package com.putoet.day6;

import com.putoet.grid.Point;

import java.util.List;

public class PointFactory {
    public static List<Point> of(List<String> csvs) {
        assert csvs != null;

        return csvs.stream()
            .map(PointFactory::of)
            .toList();
    }

    public static Point of(String csv) {
        assert csv != null;

        final String[] xy = csv.split(", ");
        assert xy.length == 2;

        return Point.of(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
    }
}
