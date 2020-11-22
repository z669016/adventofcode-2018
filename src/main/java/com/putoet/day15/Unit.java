package com.putoet.day15;

import utilities.Point;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Unit implements Comparable<Unit> {
    private final int force;
    private boolean alive = true;
    private int hitPoints;
    private Point location;

    public Unit(int hitPoints, int force, Point location) {
        assert location != null;

        this.hitPoints = hitPoints;
        this.force = force;
        this.location = location;
    }

    public abstract char token();

    public boolean isAlive() { return alive; }
    public int hitPoints() { return hitPoints; }
    public int force() { return force; }

    public void receiveHit(int force) {
        hitPoints -= force;
        alive = (hitPoints > 0);
    }

    public void attack(Unit other) {
        assert other != null;
        assert !this.getClass().equals(other.getClass());

        other.receiveHit(force);
    }

    public boolean turn(BeverageBanditsBoard board) {
        final List<Unit> enemies = board.enemies(this);
        if (enemies.size() == 0)
            return false;

        List<Unit> enemiesInRange = nextToEnemy(board);
        if (enemiesInRange.size() == 0) {
            move(board);
            enemiesInRange = nextToEnemy(board);
        }

        if (enemiesInRange.size() > 0){
            attack(weakestEnemy(enemiesInRange));
        }

        return true;
    }

    private Unit weakestEnemy(List<Unit> enemies) {
        enemies.sort(Comparator.comparingInt((Unit unit) -> unit.hitPoints)
                .thenComparingInt(unit -> unit.location.y)
                .thenComparingInt(unit -> unit.location.x)
        );
        return enemies.get(0);
    }

    protected List<Unit> nextToEnemy(BeverageBanditsBoard board) {
        final List<Point> nextTo = board.nextTo(location);
        return board.enemies(this).stream()
                .filter(enemy -> nextTo.contains(enemy.location))
                .collect(Collectors.toList());
    }

    private void move(BeverageBanditsBoard board) {
        final Optional<Point> next = UnitSearch.nextPointFor(this, board);
        next.ifPresent(point -> location = point);
    }

    public Point location() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("alive: %s, force: %d, hitPoints: %d, location: %s",
                alive, force, hitPoints, location);
    }

    @Override
    public int compareTo(Unit other) {
        int compareTo = Integer.compare(location.y, other.location.y);
        if (compareTo == 0)
            compareTo = Integer.compare(location.x, other.location.x);

        return compareTo;
    }
}
