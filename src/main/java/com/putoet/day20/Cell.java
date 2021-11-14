package com.putoet.day20;

import com.putoet.grid.Point;

public abstract class Cell {
    public final Point location;

    public Cell(Point location) {
        this.location = location;
    }

    public abstract char symbol();

    @Override
    public String toString() {
        return location.toString() + ' ' + symbol();
    }
}
