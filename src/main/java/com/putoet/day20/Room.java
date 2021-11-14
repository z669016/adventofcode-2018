package com.putoet.day20;

import com.putoet.grid.Point;

public class Room extends Cell{
    public static final char SYMBOL = '.';

    public Room(Point location) {
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
