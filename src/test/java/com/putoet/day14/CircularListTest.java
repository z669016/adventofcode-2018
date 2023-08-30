package com.putoet.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircularListTest {

    @Test
    void get() {
        final var array = new CircularList<Integer>(10);

        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
        array.add(3);
        array.add(7);
        array.add(1);
        array.add(0);
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));

        assertEquals(1, array.get(2));
        assertEquals(3, array.get(4));
        assertEquals(7, array.get(5));
    }
}