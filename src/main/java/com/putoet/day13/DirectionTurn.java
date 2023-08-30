package com.putoet.day13;

enum DirectionTurn {
    LEFT,
    STRAIGHT,
    RIGHT;

    public DirectionTurn next() {
        return switch (this) {
            case LEFT -> STRAIGHT;
            case STRAIGHT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
