package com.putoet.day10;

import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;

public class Day10 {
    public static void main(String[] args) {
        final Message message = Message.of(ResourceLines.list("/day10.txt"));
        final char[][] grid = message.decrypt();

        part1(grid);
        part2(message);
    }

    private static void part1(char[][] grid) {
        GridUtils.print(grid);
    }

    private static void part2(Message message) {
        System.out.println("Waiting time would have been " + message.decryptTime() + " seconds.");
    }
}
