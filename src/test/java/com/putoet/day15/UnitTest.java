package com.putoet.day15;

import com.putoet.grid.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    private Goblin goblin;
    private Elve elve;

    private static final Point A = Point.of(1,1);
    private static final Point B = Point.of(10,1);
    private static final Point C = Point.of(5,2);
    private static final Point D = Point.of(15,2);
    private static final Point E = Point.of(1,3);
    private static final Point F = Point.of(9,3);
    private static final Point G = Point.of(8,4);

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

    @Test
    void comparator() {
        final List<Point> sorted = List.of(A, B, C, D, E, F, G);
        final List<Point> unsorted = List.of(B, A, D, G, C, F, E);

        assertEquals(sorted, unsorted.stream().sorted(Unit.COMPARATOR).collect(Collectors.toList()));
    }

    @Test
    void readingOrderA() {
        final List<Point> sorted = List.of(A, B, C, D, E, F, G);
        final List<Point> unsorted = new ArrayList<>(List.of(B, A, D, G, C, F, E));

        Collections.sort(unsorted, Comparator.comparingInt(Unit.readingOrder(A)));
        assertEquals(sorted, unsorted);
    }

    @Test
    void toIntFunction() {
        final List<Point> sorted = List.of(A, B, C, D, E, F, G);
        final ToIntFunction<Point> readingOrder = Unit.readingOrder(D);

        sorted.forEach(p -> System.out.println(p + " " + readingOrder.applyAsInt(p)));
    }

    @Test
    void readingOrderD() {
        final List<Point> sorted = List.of(E, F, G, C, B, A);
        final List<Point> unsorted = new ArrayList<>(List.of(B, A, G, C, F, E));

        Collections.sort(unsorted, Comparator.comparingInt(Unit.readingOrder(D)));
        assertEquals(sorted, unsorted);
    }
}