package com.putoet.day20;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

class Room extends Cell {
    public static final char SYMBOL = '.';

    public Room(@NotNull Point location) {
        super(location);
    }

    public static boolean isRoom(char c) {
        return c == '.' || c =='X';
    }

    @Override
    public char symbol() {
        return SYMBOL;
    }
}
