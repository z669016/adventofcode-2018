package com.putoet.day13;

import com.putoet.grid.Point;

import java.util.List;

public class Tracks {
    public enum TrackElement {
        HLINE,
        VLINE,
        INTERSECTION,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT;

        public static TrackElement of(Direction direction, char token) {
            assert direction != null;

            return switch (token) {
                case '-' -> HLINE;
                case '|' -> VLINE;
                case '+' -> INTERSECTION;
                case '/' -> (direction == Direction.WEST || direction == Direction.NORTH ? TOP_LEFT : BOTTOM_RIGHT);
                case '\\' -> (direction == Direction.EAST || direction == Direction.NORTH ? TOP_RIGHT : BOTTOM_LEFT);
                default -> throw new IllegalArgumentException("Invalid track token '" + token + "'");
            };
        }
    }

    private final char[][] grid;

    private Tracks(char[][] grid) {
        this.grid = grid;
    }

    public TrackElement at(Direction direction, Point point) {
        assert direction != null;
        assert checkpoint(point);

        return TrackElement.of(direction, grid[point.y()][point.x()]);
    }

    private boolean checkpoint(Point point) {
        return point != null
                && point.y() >= 0 && point.y() < grid.length
                && point.x() >= 0 && point.x() <= grid[point.y()].length;
    }

    public static Tracks of(List<String> lines) {
        final char[][] grid = lines.stream()
                .map(line -> line.replace('<', '-').replace('>', '-'))
                .map(line -> line.replace('^', '|').replace('v', '|'))
                .map(String::toCharArray)
                .toArray(char[][]::new);

        return new Tracks(grid);
    }
}
