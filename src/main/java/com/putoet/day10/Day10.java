package com.putoet.day10;

import com.putoet.resources.ResourceLines;
import utilities.GridUtils;

public class Day10 {
    public static void main(String[] args) {
        final Message message = Message.of(ResourceLines.list("/day10.txt"));
        final char[][] grid = message.decrypt();

        GridUtils.print(grid);
        System.out.println("Waiting time would have been " + message.decryptTime() + " seconds.");
    }
}
