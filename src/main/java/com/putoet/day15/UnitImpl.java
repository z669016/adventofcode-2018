package com.putoet.day15;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;

import java.util.List;
import java.util.stream.Collectors;

public abstract class UnitImpl implements Unit {
    private Point position;
    private int hitPoints = 200;
    private int attackPower = 3;

    public UnitImpl(Point position) {
        assert position != null;

        this.position = position;
    }

    @Override
    public Point position() {
        return position;
    }

    @Override
    public boolean isAlive() {
        return hitPoints >= 0;
    }

    @Override
    public List<Unit> targets(List<Unit> units) {
        assert units != null;

        return units.stream()
                .filter(u -> u.symbol() != this.symbol())
                .filter(Unit::isAlive)
                .collect(Collectors.toList());
    }

    @Override
    public void move(Point point, Grid grid) {
        assert grid != null;
        assert point != null;
        assert grid.contains(point.x, point.y);
        assert grid.contains(position.x, position.y);

        grid.set(position.x, position.y, Game.EMPTY);
        position = point;
        grid.set(position.x, position.y, symbol());
    }

    @Override
    public int hitPoints() {
        return hitPoints;
    }

    @Override
    public int attackPower() {
        return attackPower;
    }

    @Override
    public void attacked(Unit attacker){
        assert isAlive();
        assert attacker.isAlive();

        hitPoints -= attacker.attackPower();
    }
}
