package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WaterFlowTest {
    @Test
    void flow() {
        final List<String> lines = ResourceLines.list("/day17.txt");
        final Grid grid = GirdFactory.of(lines);

        System.out.println(grid);
        System.out.println();

        WaterFlow.flow(grid);
        System.out.println(grid);

        assertThat(WaterFlow.tilesReached(grid)).isEqualTo(57);
        assertThat(WaterFlow.tilesStillWater(grid)).isEqualTo(29);
    }
}