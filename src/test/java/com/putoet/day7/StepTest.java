package com.putoet.day7;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StepTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Step("1"));
        assertThrows(AssertionError.class, () -> new Step("aa"));

        assertEquals("A", new Step("a").name());
    }

    @Test
    void before() {
        final var a = new Step("a");
        final var b = new Step("b");
        final var c = new Step("c");
        final var d = new Step("d");

        b.after(a);
        c.after(a);
        d.after(b);

        assertEquals(0, a.dependencyCount());
        assertEquals(1, b.dependencyCount());

        assertTrue(b.available(Set.of(a)));
        assertFalse(d.available(Set.of(c)));
        assertTrue(d.available(Set.of(a, b)));
    }
}