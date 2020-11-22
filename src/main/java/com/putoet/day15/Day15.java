package com.putoet.day15;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day15 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day15.txt");
        final int score = Game.play(lines);

        System.out.println("Score is " + score);
    }

    // 252008 is wrong
}
