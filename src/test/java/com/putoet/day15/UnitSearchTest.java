package com.putoet.day15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.putoet.utilities.Point;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UnitSearchTest {
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
    void bfs() {
        final Queue<Unit> units = board.units();
        final Unit elve = units.poll();
        assertNotNull(elve);

        final List<Point> targets = units.stream()
                .map(unit -> board.inRange(unit.location()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        final List<UnitSearch.Node> nodes = UnitSearch.bfs(elve.location(), targets, board);
        assertEquals(3, nodes.size());
        assertEquals(Point.of(3,1), nodes.get(0).state);
        assertEquals(2, nodes.get(0).steps());
        assertEquals(Point.of(2,2), nodes.get(1).state);
        assertEquals(Point.of(1,3), nodes.get(2).state);
    }

    @Test
    void nextPointFor() {
        final Queue<Unit> units = board.units();
        final Unit elve = units.poll();
        assertNotNull(elve);

        final Optional<Point> next = UnitSearch.nextPointFor(elve, board);
        assertTrue(next.isPresent());
        assertEquals(Point.of(2,1), next.get());
    }
}