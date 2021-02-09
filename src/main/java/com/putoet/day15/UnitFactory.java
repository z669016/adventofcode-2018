package com.putoet.day15;

import com.putoet.grid.Point;

public class UnitFactory {
    public static final int INITIAL_HITPOINTS = 200;
    public static final int INITIAL_FORCE = 3;

    public static Unit of(int x, int y, char token) {
        assert x >=0 && y >= 0;

        final Point point = Point.of(x, y);

        return switch (token) {
            case Goblin.TOKEN -> new Goblin(INITIAL_HITPOINTS, INITIAL_FORCE, point);
            case Elve.TOKEN -> new Elve(INITIAL_HITPOINTS, INITIAL_FORCE, point);
            default -> throw new IllegalArgumentException("Invalid unit token " + token + " at " + point);
        };
    }
}
