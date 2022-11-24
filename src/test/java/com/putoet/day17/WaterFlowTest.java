package com.putoet.day17;

import com.putoet.grid.Grid;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WaterFlowTest {
    @Test
    void flow() {
        final List<String> lines = ResourceLines.list("/day17.txt");
        final Grid grid = GirdFactory.of(lines);

        System.out.println(grid);
        System.out.println();

        WaterFlow.flow(grid);
        System.out.println(grid);

        assertEquals(57, WaterFlow.tilesReached(grid));
        assertEquals(29, WaterFlow.tilesStillWater(grid));
    }
}