package com.putoet.day9;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day9 {
    public static void main(String[] args) {
        final var line = ResourceLines.line("/day9.txt");
        final var players = players(line);
        final var lastMarble = lastMarble(line);

        Timer.run(() -> play(players, lastMarble));
        Timer.run(() -> play(players, lastMarble * 100));
    }

    private static void play(int players, int lastMarble) {
        final var stock = new Stock(lastMarble);
        final var game = new MarbleGame(stock, players);
        final var circle = new CircleDoubleLinkedList();

        game.play(circle);

        System.out.println("High score is " + game.highScore());
    }

    private static int players(String line) {
        return value(line, 0);
    }

    private static int lastMarble(String line) {
        return value(line, 6);
    }

    private static int value(String line, int idx) {
        final var words = line.split(" ");
        return Integer.parseInt(words[idx]);
    }
}
