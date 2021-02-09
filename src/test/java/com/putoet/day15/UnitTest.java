package com.putoet.day15;

import com.putoet.grid.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    private Goblin goblin;
    private Elve elve;

    @BeforeEach
    void setup() {
        goblin = new Goblin(2, 3, Point.of(0, 0));
        elve = new Elve(4, 3, Point.of(1, 0));
    }

    @Test
    void compareTo() {
        assertEquals(-1, goblin.compareTo(elve));
        assertEquals(1, elve.compareTo(goblin));
    }

    @Test
    void attack() {
        assertThrows(AssertionError.class, () -> goblin.attack(goblin));

        goblin.attack(elve);
        assertTrue(elve.isAlive());
        assertEquals(1, elve.hitPoints());
        assertEquals(2, goblin.hitPoints());

        elve.attack(goblin);
        assertFalse(goblin.isAlive());
        assertEquals(-1, goblin.hitPoints());
        assertEquals(1, elve.hitPoints());
    }

    @Test
    void nextToEnemyTwo() {
        final List<String> lines = List.of(
                "#######",
                "#.....#",
                "#.EG#.#",
                "#.G.#G#",
                "#######"
        );
        final List<Unit> enemies = enemies(lines);
        assertEquals(2, enemies.size());
        System.out.println(enemies);
    }

    @Test
    void nextToEnemyNone() {
        final List<String> lines = List.of(
                "#######",
                "#E..G.#",
                "#...#.#",
                "#.G.#G#",
                "#######"
        );
        final List<Unit> enemies = enemies(lines);
        assertEquals(0, enemies.size());
    }

    private List<Unit> enemies(List<String> lines) {
        final BeverageBanditsBoard board = BeverageBanditsBoard.of(lines);
        final Queue<Unit> units = board.units();
        final Elve elve = (Elve) units.poll();
        return elve.nextToEnemy(board);
    }
}