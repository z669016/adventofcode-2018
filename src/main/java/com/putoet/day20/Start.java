package com.putoet.day20;

import com.putoet.grid.Point;

class Start extends Cell {
    public Start() {
        super(Point.ORIGIN);
    }

    @Override
    public char symbol() {
        return 'X';
    }
}
