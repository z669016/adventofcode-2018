package com.putoet.day15;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;

import java.util.List;

public interface Unit {
    int hitPoints();
    int attackPower();
    boolean isAlive();
    char symbol();
    char enemy();
    Point position();
    void move(Point point, Grid grid);
    void attacked(Unit attacker);

    List<Unit> targets(List<Unit> units);
}
