package com.putoet.day15;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitFactoryTest {

    @Test
    void ofError() {
        assertThrows(AssertionError.class, () -> UnitFactory.of(-1, 0, 'E'));
        assertThrows(AssertionError.class, () -> UnitFactory.of(0, -1, 'G'));
        assertThrows(IllegalArgumentException.class, () -> UnitFactory.of(0, 0, 'A'));
    }

    @Test
    void eLve() {
        final Unit elve = UnitFactory.of(3, 5, 'E');
        assertTrue(elve instanceof Elve);
        assertEquals(Point.of(3, 5), elve.location());
        assertEquals('E', elve.token());
    }

    void goblin() {
        final Unit goblin = UnitFactory.of(3, 5, 'G');
        assertTrue(goblin instanceof Goblin);
        assertEquals(Point.of(3, 5), goblin.location());
        assertEquals('G', goblin.token());
    }
}