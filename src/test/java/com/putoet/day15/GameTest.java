package com.putoet.day15;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    private static final List<String> GRID1 = List.of(
            "#######",
            "#.G...#",
            "#...EG#",
            "#.#.#G#",
            "#..G#E#",
            "#.....#",
            "#######"
    );

    private static final List<String> GRID2 = List.of(
            "#######",
            "#G..#E#",
            "#E#E.E#",
            "#G.##.#",
            "#...#E#",
            "#...E.#",
            "#######"
    );

    private static final List<String> GRID3 = List.of(
            "#######",
            "#E..EG#",
            "#.#G.E#",
            "#E.##E#",
            "#G..#.#",
            "#..E#.#",
            "#######"
    );

    private static final List<String> GRID4 = List.of(
            "#######",
            "#E.G#.#",
            "#.#G..#",
            "#G.#.G#",
            "#G..#.#",
            "#...E.#",
            "#######"
    );

    private static final List<String> GRID5 = List.of(
            "#######",
            "#.E...#",
            "#.#..G#",
            "#.###.#",
            "#E#G#G#",
            "#...#G#",
            "#######"
    );

    private static final List<String> GRID6 = List.of(
            "#########",
            "#G......#",
            "#.E.#...#",
            "#..##..G#",
            "#...##..#",
            "#...#...#",
            "#.G...G.#",
            "#.....G.#",
            "#########"
    );

    @Test
    void combat1() {
        combat(GRID1, UnitType.GOBLIN, 27730);
    }

    @Test
    void combat2() {
        combat(GRID2, UnitType.ELF, 36334);
    }

    @Test
    void combat3() {
        combat(GRID3, UnitType.ELF, 39514);
    }

    @Test
    void combat4() {
        combat(GRID4, UnitType.GOBLIN, 27755);
    }

    @Test
    void combat5() {
        combat(GRID5, UnitType.GOBLIN, 28944);
    }

    @Test
    void combat6() {
        combat(GRID6, UnitType.GOBLIN, 18740);
    }

    void combat(List<String> grid, UnitType type, int score) {
        final Game game = GameFactory.of(grid);
        final Pair<UnitType, Integer> winner = game.combat();
        assertEquals(type, winner.getValue0());
        assertEquals(score, winner.getValue1());
    }
}