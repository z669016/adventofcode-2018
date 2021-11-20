package com.putoet.day15;

import com.putoet.grid.Point;

public class Unit {
    private final UnitType type;
    private final int attackPower;

    private int hitPoints = 200;
    private Point point;

    public Unit(UnitType type, Point point) {
        assert type != null;
        assert point != null;

        this.type = type;
        this.point = point;
        this.attackPower = 3;
    }

    public Unit(UnitType type, Point point, int attackPower) {
        assert type != null;
        assert point != null;

        this.type = type;
        this.point = point;
        this.attackPower = attackPower;
    }

    public UnitType type() { return type; }
    public int hitPoints() { return hitPoints; }
    public int attackPower() { return attackPower; }
    public Point point() { return point; }
    public boolean alive() { return hitPoints > 0; }

    public Unit defend(Unit attacker) {
        assert hitPoints > 0;
        assert type != attacker.type;

        hitPoints = Math.max(0, hitPoints - attacker.attackPower);

        return this;
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
