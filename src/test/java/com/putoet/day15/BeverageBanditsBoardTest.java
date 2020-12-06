package com.putoet.day15;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.putoet.utilities.Point;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class BeverageBanditsBoardTest {
    private BeverageBanditsBoard board;
    public static List<String> LINES = List.of(
            "#######",
            "#E..G.#",
            "#...#.#",
            "#.G.#G#",
            "#######"
    );

    @BeforeEach
    void setup() {
        board = BeverageBanditsBoard.of(LINES);
    }

    @Test
    void of() {
        final BeverageBanditsBoard board = BeverageBanditsBoard.of(ResourceLines.list("/day15.txt"));
        final Queue<Unit> units = board.units();

        assertEquals(7, units.size());

        Unit unit = units.poll();
        assertTrue(unit instanceof Goblin);
        assertEquals(Point.of(2,1), unit.location());

        unit = units.poll();
        assertTrue(unit instanceof Elve);
        assertEquals(Point.of(4,1), unit.location());

        unit = units.poll();
        assertTrue(unit instanceof Elve);
        assertEquals(Point.of(1,2), unit.location());

        unit = units.poll();
        assertTrue(unit instanceof Goblin);
        assertEquals(Point.of(3,2), unit.location());

        unit = units.poll();
        assertTrue(unit instanceof Elve);
        assertEquals(Point.of(5,2), unit.location());

        unit = units.poll();
        assertTrue(unit instanceof Goblin);
        assertEquals(Point.of(2,3), unit.location());

        unit = units.poll();
        assertTrue(unit instanceof Elve);
        assertEquals(Point.of(4,3), unit.location());
    }

    @Test
    void nextTo() {
        final List<Point> nextTo = board.nextTo(Point.of(4,1));
        assertEquals(List.of(Point.of(3,1), Point.of(5,1)), nextTo);
    }

    @Test
    void enemies() {
        final Queue<Unit> units = board.units();
        final Unit elve = units.poll();
        final Unit goblin = units.poll();

        assertEquals(3, board.enemies(elve).size());
        assertEquals(1, board.enemies(goblin).size());
    }
}