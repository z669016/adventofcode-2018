package com.putoet.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void placeLinked() {
        final var circle = new CircleDoubleLinkedList();
        test(circle);
    }

    private void test(Circle circle) {
        for (var idx = 1; idx < 23; idx++) {
            assertEquals(0, circle.place(idx));
        }

        assertEquals("0 16 8 17 4 18 9 19 2 20 10 21 5 (22) 11 1 12 6 13 3 14 7 15 ", circle.toString());

        final var score = circle.place(23);
        assertEquals(32, score);
    }
}