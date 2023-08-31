package com.putoet.day20;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

abstract class Cell {
    public final Point location;

    public Cell(@NotNull Point location) {
        this.location = location;
    }

    public abstract char symbol();

    @Override
    public String toString() {
        return location.toString() + ' ' + symbol();
    }
}
