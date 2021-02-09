package com.putoet.day15;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day15 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day15.txt");
        final int score = Game.play(lines);

        System.out.println("Score is " + score);
    }

    // 252_008 is wrong (109 full rounds)
    // 254_320 is too high (same score but using 110 full rounds, which should be wrong as well)
    // 193_563 is not correct (selecting next in reading order, starting from the current position, not from top-left
    // 193_362 is not correct (same as previous but fixed selection
    // 250_344 is not correct
}
