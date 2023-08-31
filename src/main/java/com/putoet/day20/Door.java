package com.putoet.day20;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

class Door extends Cell {
    final char symbol;

    public Door(@NotNull Point location, @NotNull Point direction) {
        super(location);
        this.symbol = direction.equals(Point.NORTH) || direction.equals(Point.SOUTH) ? '-' : '|';
    }

    public static boolean isDoor(char c) {
        return c =='-' || c == '|';
    }

    @Override
    public char symbol() {
        return symbol;
    }
}
