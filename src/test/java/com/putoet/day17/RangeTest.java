package com.putoet.day17;

import org.junit.jupiter.api.Test;
import com.putoet.utilities.Grid;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RangeTest {

    @Test
    void ofXLine() {
        final String xLine = "x=569, y=570..582";

        Range range = Range.of(xLine);
        assertThat(range instanceof YRange).isTrue();
        assertThat(range.minX()).isEqualTo(569);
        assertThat(range.maxX()).isEqualTo(569);
        assertThat(range.minY()).isEqualTo(570);
        assertThat(range.maxY()).isEqualTo(582);
        assertThat(range.toString()).isEqualTo(xLine);

        final Grid grid = mock(Grid.class);
        range.apply(grid);
        for (int y = 570; y <= 582; y++)
            verify(grid, times(1)).set(569, y, '#');
    }

    @Test
    void ofYLine() {
        final String yLine = "y=372, x=495..519";

        Range range = Range.of(yLine);
        assertThat(range instanceof XRange).isTrue();
        assertThat(range.minX()).isEqualTo(495);
        assertThat(range.maxX()).isEqualTo(519);
        assertThat(range.minY()).isEqualTo(372);
        assertThat(range.maxY()).isEqualTo(372);
        assertThat(range.toString()).isEqualTo(yLine);

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