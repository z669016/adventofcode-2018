package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GirdFactoryTest {

    @Test
    void of() {
        final var lines = ResourceLines.list("/day17.txt");
        final var grid = GirdFactory.of(lines);

        assertEquals(494, grid.minX());
        assertEquals(508, grid.maxX());
        assertEquals(0, grid.minY());
        assertEquals(14, grid.maxY());
        assertEquals('+', grid.get(GirdFactory.WATER_X, GirdFactory.WATER_Y));

        System.out.println(grid);
    }
}