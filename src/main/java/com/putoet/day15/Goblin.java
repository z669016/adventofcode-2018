package com.putoet.day15;

import com.putoet.grid.Point;

public class Goblin extends UnitImpl {
    public static final char SYMBOL = 'G';

    public Goblin (Point position) {
        super(position);
    }

    @Override
    public char symbol() {
        return SYMBOL;
    }

    @Override
    public char enemy() { return Elf.SYMBOL; }
}
