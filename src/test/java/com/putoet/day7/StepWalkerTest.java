package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StepWalkerTest {

    @Test
    void walking() {
        final var walker = new StepWalker(new Step("C"), 0);
        assertEquals(3, walker.walking());
    }

    @Test
    void walk() {
        final var walker = new StepWalker(new Step("C"), 0);
        assertEquals(3, walker.walking());

        var walk = walker.walk();
        assertFalse(walk.isPresent());

        walk = walker.walk();
        assertFalse(walk.isPresent());

        walk = walker.walk();
        assertTrue(walk.isPresent());
        assertEquals("C", walk.get());
    }
}