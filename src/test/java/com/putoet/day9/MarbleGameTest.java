package com.putoet.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarbleGameTest {

    @Test
    void play() {
        final Stock stock = new Stock(25);
        final MarbleGame game = new MarbleGame(stock, 9);
        final Circle circle = new CircleDoubleLinkedList();

        game.play(circle);

        assertEquals(9, game.players());
        assertArrayEquals(new long[] {0, 0, 0, 0, 32, 0, 0, 0, 0}, game.scores());
        assertEquals(32, game.highScore());
    }

    @Test
    void testCases() {
        assertEquals(8317, playGame(10, 1618));
        assertEquals(146373, playGame(13, 7999));
        assertEquals(2764, playGame(17, 1104));
        assertEquals(54718, playGame(21, 6111));
        assertEquals(37305, playGame(30, 5807));
    }

    private long playGame(int players, int lastMarble) {
        final Stock stock = new Stock(lastMarble);
        final MarbleGame game = new MarbleGame(stock, players);
        final Circle circle = new CircleDoubleLinkedList();

        game.play(circle);

        return game.highScore();
    }
}