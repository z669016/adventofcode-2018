package com.putoet.day13;

import org.jetbrains.annotations.NotNull;

enum TrackElement {
    HORIZONTAL_LINE,
    VERTICAL_LINE,
    INTERSECTION,
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT;

    public static TrackElement of(@NotNull Direction direction, char token) {
        return switch (token) {
            case '-' -> HORIZONTAL_LINE;
            case '|' -> VERTICAL_LINE;
            case '+' -> INTERSECTION;
            case '/' -> (direction == Direction.WEST || direction == Direction.NORTH ? TOP_LEFT : BOTTOM_RIGHT);
            case '\\' -> (direction == Direction.EAST || direction == Direction.NORTH ? TOP_RIGHT : BOTTOM_LEFT);
            default -> throw new IllegalArgumentException("Invalid track token '" + token + "'");
        };
    }
}
