package com.putoet.day9;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day9 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day9.txt");
        final int players = players(lines.get(0));
        final int lastMarble = lastMarble(lines.get(0));

        play(players, lastMarble);
        play(players, lastMarble * 100);
    }

    private static void play(int players, int lastMarble) {
        final Stock stock = new Stock(lastMarble);
        final MarbleGame game = new MarbleGame(stock, players);
        final Circle circle = new CircleDoubleLinkedList();

        final long start = System.currentTimeMillis();
        game.play(circle);
        final long end = System.currentTimeMillis();

        System.out.println("High score is " + game.highScore());
        System.out.println("The game took " + (end- start) +" ms");
        System.out.println("Circle size is " + circle.size());
    }

    private static int players(String line) {
        return value(line, 0);
    }

    private static int lastMarble(String line) {
        return value(line, 6);
    }

    private static int value(String line, int idx) {
        final String[] words = line.split(" ");
        return Integer.parseInt(words[idx]);
    }

}
