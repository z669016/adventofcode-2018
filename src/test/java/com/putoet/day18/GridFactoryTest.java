package com.putoet.day18;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GridFactoryTest {

    @Test
    void of() {
        final var grid = GridFactory.of(ResourceLines.list("/day18.txt"));
        assertEquals(0, grid.minX());
        assertEquals(10, grid.maxX());
        assertEquals(0, grid.minY());
        assertEquals(10, grid.maxY());
        assertNotNull(grid.grid());
    }

    @Test
    void next() {
        var next = GridFactory.of(ResourceLines.list("/day18.txt"));
        for (var count = 0; count < 10; count++)
            next = GridFactory.next(next);

        assertEquals(1147, GridFactory.totalResourceValue(next));
    }
}