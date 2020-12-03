package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import utilities.Grid;

import java.util.List;

public class Day17 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day17.txt");
        final Grid grid = GirdFactory.of(lines);

        System.out.println(grid);
        System.out.println();

        WaterFlow.flow(grid);
        System.out.println(grid);

        System.out.println("Tiles reached is " + WaterFlow.tilesReached(grid));
        System.out.println("Tiles with still water is " + WaterFlow.tilesStillWater(grid));
    }
}
