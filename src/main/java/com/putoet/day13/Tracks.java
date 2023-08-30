package com.putoet.day13;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;

record Tracks(char[][] grid) {
    public TrackElement at(@NotNull Direction direction, @NotNull Point point) {
        assert checkpoint(point);

        return TrackElement.of(direction, grid[point.y()][point.x()]);
    }

    private boolean checkpoint(Point point) {
        return point.y() >= 0 && point.y() < grid.length &&
               point.x() >= 0 && point.x() <= grid[point.y()].length;
    }

    public static Tracks of(List<String> lines) {
        final var grid = lines.stream()
                .map(line -> line.replace('<', '-').replace('>', '-'))
                .map(line -> line.replace('^', '|').replace('v', '|'))
                .map(String::toCharArray)
                .toArray(char[][]::new);

        return new Tracks(grid);
    }
}
