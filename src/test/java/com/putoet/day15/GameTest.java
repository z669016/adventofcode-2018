package com.putoet.day15;

import com.putoet.grid.Point;
import com.putoet.search.GenericSearch;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static final List<String> TEST_GAME_1 = List.of(
            "#######",
            "#.G...#",
            "#...EG#",
            "#.#.#G#",
            "#..G#E#",
            "#.....#",
            "#######"
    );

    private static final List<String> TEST_GAME_2 = List.of(
            "#######",
            "#G..#E#",
            "#E#E.E#",
            "#G.##.#",
            "#...#E#",
            "#...E.#",
            "#######"
    );

    @Test
    void testToString() {
        final Game game = GameFactory.of(TEST_GAME_1);

        assertEqualsGrid(TEST_GAME_1, game);
        assertEquals(6, game.units.size());
        assertEqualsUnit(Goblin.SYMBOL, Point.of(2, 1), game.units.get(0));
        assertEqualsUnit(Elf.SYMBOL, Point.of(4, 2), game.units.get(1));
        assertEqualsUnit(Goblin.SYMBOL, Point.of(5, 2), game.units.get(2));
        assertEqualsUnit(Goblin.SYMBOL, Point.of(5, 3), game.units.get(3));
        assertEqualsUnit(Goblin.SYMBOL, Point.of(3, 4), game.units.get(4));
        assertEqualsUnit(Elf.SYMBOL, Point.of(5, 4), game.units.get(5));
    }

    @Test
    void inRange() {
        final Game game = GameFactory.of(TEST_GAME_1);
        final Unit unit = game.units.get(0);
        final List<Unit> targets = unit.targets(game.units);

        assertEquals(List.of(Point.of(4, 1), Point.of(3, 2), Point.of(5, 5)), game.inRange(targets));
    }

    @Test
    void nearest() {
        final Game game = GameFactory.of(TEST_GAME_1);
        final Unit unit = game.units.get(0);
        final List<Unit> targets = unit.targets(game.units);
        final List<Point> inRange = game.inRange(targets);
        final Optional<GenericSearch.Node<Point>> nearest = game.nearest(unit, inRange);

        assertTrue(nearest.isPresent());
        assertEquals(Point.of(4,1), nearest.get().state);
        System.out.println(nearest.get().path());
    }

    @Test
    void target() {
        final char[][] grid = {
                {'#', '#', '#', '#', '#'},
                {'#', 'E', '.', 'E', '#'},
                {'#', '.', 'G', '.', '#'},
                {'#', 'E', '.', 'E', '#'},
                {'#', '#', '#', '#', '#'}
        };

        // Diagonal attack isn't allowed, so no targets should be found
        assertFalse(findTarget(grid).isPresent());

        // Add targets counter clock wise and every time the last added target should be found
        grid[3][2] = 'E';
        checkTarget(grid, 2, 3);

        grid[2][3] = 'E';
        checkTarget(grid, 3, 2);

        grid[2][1] = 'E';
        checkTarget(grid, 1, 2);

        grid[1][2] = 'E';
        checkTarget(grid, 2, 1);
    }

    private void checkTarget(char[][] grid,int x,int y) {
        grid[y][x] = Elf.SYMBOL;
        Optional<Unit> target = findTarget(grid);
        assertTrue(target.isPresent());
        assertEquals(Point.of(x, y), target.get().position());
    }

    private Optional<Unit> findTarget(char[][] grid) {
        final Game game = GameFactory.of(grid);
        final Unit goblin = game.units.stream().filter(u ->u instanceof Goblin).findFirst().get();
        return game.adjacent(goblin);
    }

    private void assertEqualsUnit(char symbol, Point point, Unit unit) {
        assertEquals(symbol, unit.symbol());
        assertEquals(point, unit.position());
    }

    private void assertEqualsGrid(List<String> expected, Game game) {
        for (int y = 0; y < expected.size(); y++)
            for (int x = 0; x < expected.get(0).length();x++)
                assertEquals(expected.get(y).charAt(x), game.grid.get(x, y));
    }

    @Test
    void battle() {
        battle(TEST_GAME_1,27730);
        // battle(TEST_GAME_2,36334);
    }

    private void battle(List<String> map, int expectedScore) {
        final Game game = GameFactory.of(map);
        game.dump();
        int score = game.battle();
        game.dump();
        assertEquals(expectedScore, score);
    }
}