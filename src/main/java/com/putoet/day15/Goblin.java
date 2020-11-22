package com.putoet.day15;

import utilities.Point;

public class Goblin extends Unit {
    public static final char TOKEN = 'G';

    public Goblin(int hitPoints, int force, Point location) {
        super(hitPoints, force, location);
    }

    @Override
    public char token() {
        return TOKEN;
    }

    @Override
    public String toString() {
        return "Goblin " + super.toString();
    }
}
