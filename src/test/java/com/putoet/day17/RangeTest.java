package com.putoet.day17;

import com.putoet.grid.Grid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RangeTest {

    @Test
    void ofXLine() {
        final String xLine = "x=569, y=570..582";

        Range range = Range.of(xLine);
        assertTrue(range instanceof YRange);
        assertEquals(569, range.minX());
        assertEquals(569, range.maxX());
        assertEquals(570, range.minY());
        assertEquals(582, range.maxY());
        assertEquals(xLine, range.toString());

        final Grid grid = mock(Grid.class);
        range.apply(grid);
        for (int y = 570; y <= 582; y++)
            verify(grid, times(1)).set(569, y, '#');
    }

    @Test
    void ofYLine() {
        final String yLine = "y=372, x=495..519";

        Range range = Range.of(yLine);
        assertTrue(range instanceof XRange);
        assertEquals(495, range.minX());
        assertEquals(519, range.maxX());
        assertEquals(372, range.minY());
        assertEquals(372, range.maxY());
        assertEquals(yLine, range.toString());

        final Grid grid = mock(Grid.class);
        range.apply(grid);
        for (int x = 495; x <= 519; x++)
            verify(grid, times(1)).set(x, 372, '#');
    }

    @Test
    void ofErrorLine() {
        assertThrows(IllegalArgumentException.class, () -> Range.of("z=372, x=495..519"));
        assertThrows(IllegalArgumentException.class, () -> Range.of("x=372, z=495..519"));
        assertThrows(IllegalArgumentException.class, () -> Range.of("x=372, x=495..519"));
        assertThrows(IllegalArgumentException.class, () -> Range.of("x=a, x=495..519"));
        assertThrows(IllegalArgumentException.class, () -> Range.of("x=372, x=a..519"));
        assertThrows(IllegalArgumentException.class, () -> Range.of("x=372, x=495..a"));
    }
}