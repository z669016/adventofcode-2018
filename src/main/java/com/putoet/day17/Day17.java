package com.putoet.day17;

import com.putoet.grid.Grid;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day17 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day17.txt");
        final Grid grid = GirdFactory.of(lines);

        WaterFlow.flow(grid);

        part1(grid);
        part2(grid);
    }

    private static void part2(Grid grid) {
        System.out.println("Tiles with still water is " + WaterFlow.tilesStillWater(grid));
    }

    private static void part1(Grid grid) {
        System.out.println("Tiles reached is " + WaterFlow.tilesReached(grid));
    }
}
