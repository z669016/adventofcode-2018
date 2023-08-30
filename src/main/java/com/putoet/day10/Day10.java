package com.putoet.day10;

import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day10 {
    public static void main(String[] args) {
        final var message = Message.of(ResourceLines.list("/day10.txt"));

        Timer.run(() -> {
            final var grid = message.decrypt();
            GridUtils.print(grid);
        });

        Timer.run(() -> System.out.println("Waiting time would have been " + message.decryptTime() + " seconds."));
    }
}
