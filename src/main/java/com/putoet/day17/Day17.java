package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day17 {
    public static void main(String[] args) {
        final var grid = GirdFactory.of(ResourceLines.list("/day17.txt"));

        Timer.run(() -> {
            WaterFlow.flow(grid);

            System.out.println("Tiles reached is " + WaterFlow.tilesReached(grid));
            System.out.println("Tiles with still water is " + WaterFlow.tilesStillWater(grid));
        });
    }
}
