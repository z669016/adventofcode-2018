package com.putoet.day22;

import com.putoet.grid.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day22Test {
    public static final int DEPTH = 510;
    public static final Point TARGET = Point.of(10, 10);
    private Region[][] grid;

    @BeforeEach
    void setUp() {
        grid = CaveFactory.of(DEPTH, TARGET);
    }

    @Test
    void targetAreaRiskLevel() {
        assertEquals(114, Day22.targetAreaRiskLevel(grid, TARGET));
    }
}