package com.putoet.day15;

import com.putoet.grid.Point;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
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

    public static final Comparator<Point> COMPARATOR =
            Comparator.comparingInt((Point point) -> point.y).thenComparingInt((Point point) -> point.x);

    public abstract char token();

    public boolean isAlive() { return alive; }
    public int hitPoints() { return hitPoints; }

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
        final List<Unit> enemies = board.targets(this);
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

    protected List<Unit> nextToEnemy(BeverageBanditsBoard board) {
        return board.targets(this).stream()
                .filter(enemy -> location.manhattanDistance(enemy.location) == 1)
                .collect(Collectors.toList());
    }

    private Unit weakestEnemy(List<Unit> enemies) {
        assert enemies != null && enemies.size() > 0;

        if (enemies.size() == 1)
            return enemies.get(0);

        enemies.sort(Comparator.comparingInt((Unit unit) -> unit.hitPoints)
                .thenComparing(Unit.readingOrder(this))
        );
        return enemies.get(0);
    }

    private void move(BeverageBanditsBoard board) {
        final Optional<Point> next = Game.nextPointFor(this, board);
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
        return COMPARATOR.compare(this.location, other.location);
    }

    public static ToIntFunction<Point> readingOrder(Point current) {
        final Function<Point,Integer> offset = (Point point) -> point.y * 100 + point.x;
        final int base = offset.apply(current);

        return (Point point) -> offset.apply(point) - base >= 0 ?
                offset.apply(point) - base : Integer.MAX_VALUE - offset.apply(point);
    }

    public static Function<Unit,Integer> readingOrder(Unit current) {
        final ToIntFunction<Point> readingOrder = readingOrder(current.location);
        return unit -> readingOrder.applyAsInt(unit.location);
    }
}
