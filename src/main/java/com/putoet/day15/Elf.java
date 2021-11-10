package com.putoet.day15;

import com.putoet.grid.Point;

public class Elf extends UnitImpl {
    public static final char SYMBOL = 'E';

    public Elf(Point position) {
        super(position);
    }

    @Override
    public char symbol() {
        return SYMBOL;
    }

    @Override
    public char enemy() { return Goblin.SYMBOL; }
}
