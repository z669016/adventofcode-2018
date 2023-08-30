package com.putoet.day9;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

class MarbleGame {
    private final Stock stock;
    private final long[] scores;

    public MarbleGame(@NotNull Stock stock, int players) {
        assert players > 0;

        this.stock = stock;
        this.scores = new long[players];
    }

    public int players() { return scores.length; }
    public long[] scores() { return scores; }

    public void play(Circle circle) {
        var player = 0;
        while(stock.hasNext()) {
            scores[player] = scores[player] + circle.place(stock.next());
            player = nextPlayer(player);
        }
    }

    private int nextPlayer(int player) {
        return (player + 1) % players();
    }

    public long highScore() {
        return Arrays.stream(scores).max().orElseThrow();
    }
}
