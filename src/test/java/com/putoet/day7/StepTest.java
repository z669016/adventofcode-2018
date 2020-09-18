package com.putoet.day7;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StepTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Step(null));
        assertThrows(AssertionError.class, () -> new Step("1"));
        assertThrows(AssertionError.class, () -> new Step("aa"));

        assertEquals("A", new Step("a").name());
    }

    @Test
    void before() {
        final Step a = new Step("a");
        final Step b = new Step("b");
        final Step c = new Step("c");
        final Step d = new Step("d");

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