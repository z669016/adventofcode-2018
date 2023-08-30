package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WaterFlowTest {
    @Test
    void flow() {
        final var lines = ResourceLines.list("/day17.txt");
        final var grid = GirdFactory.of(lines);

        System.out.println(grid);
        System.out.println();

        WaterFlow.flow(grid);
        System.out.println(grid);

        assertEquals(57, WaterFlow.tilesReached(grid));
        assertEquals(29, WaterFlow.tilesStillWater(grid));
    }
}