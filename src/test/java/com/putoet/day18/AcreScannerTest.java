package com.putoet.day18;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AcreScannerTest {
    private Grid grid;

    @BeforeEach
    void setup() {
        grid = GridFactory.of(ResourceLines.list("/day18.txt"));
    }

    @Test
    void scan() {
        final var acreScans = AcreScanner.scan(grid);
        assertEquals(100, acreScans.size());
    }

    @Test
    void scanPoint() {
        final var lines = List.of(
                ".|#",
                "||#",
                "#|#"
        );

        final var grid = GridFactory.of(lines);
        final var acreScan = AcreScanner.scanPoint(Point.of(1, 1), grid);

        assertEquals(4, acreScan.adjacentLumberyards());
        assertEquals(3, acreScan.adjacentTrees());
        assertEquals(1, acreScan.adjacentOpen());
    }

    @Test
    void adjacendPoints() {
        var adjacentPoints = AcreScanner.adjacendPoints(Point.ORIGIN, grid);
        assertEquals(3, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(1, 0), grid);
        assertEquals(5, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(9, 0), grid);
        assertEquals(3, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(9, 1), grid);
        assertEquals(5, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(9, 9), grid);
        assertEquals(3, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(9, 9), grid);
        assertEquals(3, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(1, 9), grid);
        assertEquals(5, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(0, 9), grid);
        assertEquals(3, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(0, 8), grid);
        assertEquals(5, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(0, 1), grid);
        assertEquals(5, adjacentPoints.size());

        adjacentPoints = AcreScanner.adjacendPoints(Point.of(1, 1), grid);
        assertEquals(8, adjacentPoints.size());
    }
}