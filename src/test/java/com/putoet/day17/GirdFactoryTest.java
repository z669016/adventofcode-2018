package com.putoet.day17;

import com.putoet.grid.Grid;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GirdFactoryTest {

    @Test
    void of() {
        final List<String> lines = ResourceLines.list("/day17.txt");
        final Grid grid = GirdFactory.of(lines);

        assertThat(grid.minX()).isEqualTo(494);
        assertThat(grid.maxX()).isEqualTo(508);
        assertThat(grid.minY()).isEqualTo(0);
        assertThat(grid.maxY()).isEqualTo(14);
        assertThat(grid.get(GirdFactory.WATER_X, GirdFactory.WATER_Y)).isEqualTo('+');

        System.out.println(grid);
    }
}