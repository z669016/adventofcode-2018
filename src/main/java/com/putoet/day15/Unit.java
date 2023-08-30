package com.putoet.day15;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

class Unit {
    private final UnitType type;
    private final int attackPower;

    private int hitPoints = 200;
    private Point point;

    public Unit(@NotNull UnitType type, @NotNull Point point) {
        this.type = type;
        this.point = point;
        this.attackPower = 3;
    }

    public Unit(@NotNull UnitType type, @NotNull Point point, int attackPower) {
        this.type = type;
        this.point = point;
        this.attackPower = attackPower;
    }

    public UnitType type() { return type; }
    public int hitPoints() { return hitPoints; }
    public Point point() { return point; }
    public boolean alive() { return hitPoints > 0; }

    public void defend(@NotNull Unit attacker) {
        assert hitPoints > 0;
        assert type != attacker.type;

        hitPoints = Math.max(0, hitPoints - attacker.attackPower);
    }

    public Unit move(Point to) {
        point = to;
        return this;
    }

    @Override
    public String toString() {
        return type() + "(" + hitPoints + ")/" + point;
    }
}
