package com.putoet.day22;

import com.putoet.grid.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegionTest {
    public static final int DEPTH = 510;
    public static final Point TARGET = Point.of(10, 10);

    private static final List<String> DUMP = List.of(
            "M=.|=.|.|=.|=|=.",
            ".|=|=|||..|.=...",
            ".==|....||=..|==",
            "=.|....|.==.|==.",
            "=|..==...=.|==..",
            "=||.=.=||=|=..|=",
            "|.=.===|||..=..|",
            "|..==||=.|==|===",
            ".=..===..=|.|||.",
            ".======|||=|=.|=",
            ".===|=|===T===||",
            "=|||...|==..|=.|",
            "=.=|=.=..=.||==|",
            "||=|=...|==.=|==",
            "|=.=||===.|||===",
            "||.|==.|.|.||=||"
    );

    private Region[][] grid;

    @BeforeEach
    void setUp() {
        grid = CaveFactory.of(DEPTH, TARGET);
    }

    @Test
    void samples() {
        // At 0,0, the geologic index is 0. The erosion level is (0 + 510) % 20183 = 510. The type is 510 % 3 = 0, rocky.
        Region region = grid[0][0];
        assertEquals(510, region.erosionLevel());
        assertEquals(RegionType.ROCKY, region.type());

        // At 1,0, because the Y coordinate is 0, the geologic index is 1 * 16807 = 16807.
        // The erosion level is (16807 + 510) % 20183 = 17317. The type is 17317 % 3 = 1, wet.
        region = grid[0][1];
        assertEquals(16807, region.geologicIndex());
        assertEquals(17317, region.erosionLevel());
        assertEquals(RegionType.WET, region.type());

        // At 0,1, because the X coordinate is 0, the geologic index is 1 * 48271 = 48271.
        // The erosion level is (48271 + 510) % 20183 = 8415. The type is 8415 % 3 = 0, rocky.
        region = grid[1][0];
        assertEquals(48271, region.geologicIndex());
        assertEquals(8415, region.erosionLevel());
        assertEquals(RegionType.ROCKY, region.type());

        // At 1,1, neither coordinate is 0 and it is not the coordinate of the target, so the geologic
        // index is the erosion level of 0,1 (8415) times the erosion level of 1,0 (17317), 8415 * 17317 = 145722555.
        // The erosion level is (145722555 + 510) % 20183 = 1805. The type is 1805 % 3 = 2, narrow.
        region = grid[1][1];
        assertEquals(145722555, region.geologicIndex());
        assertEquals(1805, region.erosionLevel());
        assertEquals(RegionType.NARROW, region.type());

        // At 10,10, because they are the target's coordinates, the geologic index is 0.
        // The erosion level is (0 + 510) % 20183 = 510. The type is 510 % 3 = 0, rocky.
        region = grid[10][10];
        assertEquals(0, region.geologicIndex());
        assertEquals(510, region.erosionLevel());
        assertEquals(RegionType.ROCKY, region.type());

        // dump
        assertEquals(DUMP, CaveFactory.dump(grid, DUMP.size()));
    }
}