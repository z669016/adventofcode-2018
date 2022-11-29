package com.putoet.day13;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Direction of(char c) {
        return switch (c) {
            case '^' -> NORTH;
            case '>' -> EAST;
            case 'v' -> SOUTH;
            case '<' -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction token '" + c + "'");
        };
    }
}
