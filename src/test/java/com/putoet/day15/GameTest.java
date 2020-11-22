package com.putoet.day15;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void playOne() {
        final List<String> lines = List.of(
                "#######",
                "#.G...#",
                "#...EG#",
                "#.#.#G#",
                "#..G#E#",
                "#.....#",
                "#######"
        );
        final int score = Game.play(lines);
        assertEquals(27730, score);
    }

    @Test
    void playTwo() {
        final List<String> lines = List.of(
                "#######",
                "#G..#E#",
                "#E#E.E#",
                "#G.##.#",
                "#...#E#",
                "#...E.#",
                "#######"
        );
        final int score = Game.play(lines);
        assertEquals(36334, score);
    }

    @Test
    void playThree() {
        final List<String> lines = List.of(
                "#######",
                "#E..EG#",
                "#.#G.E#",
                "#E.##E#",
                "#G..#.#",
                "#..E#.#",
                "#######"
        );
        final int score = Game.play(lines);
        assertEquals(39514, score);
    }

    @Test
    void playFour() {
        final List<String> lines = List.of(
                "#######",
                "#E.G#.#",
                "#.#G..#",
                "#G.#.G#",
                "#G..#.#",
                "#...E.#",
                "#######"
        );
        final int score = Game.play(lines);
        assertEquals(27755, score);
    }

    @Test
    void playFive() {
        final List<String> lines = List.of(
                "#######",
                "#.E...#",
                "#.#..G#",
                "#.###.#",
                "#E#G#G#",
                "#...#G#",
                "#######"
        );
        final int score = Game.play(lines);
        assertEquals(28944, score);
    }

    @Test
    void playSix() {
        final List<String> lines = List.of(
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
        final int score = Game.play(lines);
        assertEquals(18740, score);
    }
}