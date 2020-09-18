package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StepsTest {
    private Steps steps;

    @BeforeEach
    void setup() {
        steps = new Steps();

        steps.get("b").after(steps.get("a"));
        steps.get("c").after(steps.get("a"));
        steps.get("d").after(steps.get("b"));
    }

    @Test
    void next() {
        assertEquals(4, steps.size());

        Optional<Step> next = steps.next(Set.of());
        assertTrue(next.isPresent());
        assertEquals("A", next.get().name());

        assertEquals(3, steps.size());

        assertEquals("B", steps.next(Set.of(next.get())).get().name());
    }

    @Test
    void of() {
        final Steps steps = Steps.of(ResourceLines.list("/day7.txt"));
        assertEquals(6, steps.size());
        assertEquals("C", steps.next(Set.of()).get().name());
    }
}