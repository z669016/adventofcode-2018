package com.putoet.day15;

import com.putoet.grid.Point;

public class Elve extends Unit {
    public static final char TOKEN = 'E';

    public Elve(int hitPoints, int force, Point location) {
        super(hitPoints, force, location);
    }

    @Override
    public char token() {
        return TOKEN;
    }

    @Override
    public String toString() {
        return "Elve " + super.toString();
    }
}
