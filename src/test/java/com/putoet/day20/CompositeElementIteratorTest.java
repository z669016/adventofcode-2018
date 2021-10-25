package com.putoet.day20;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CompositeElementIteratorTest {
    private static final String ELEMENT = "ENWWW";
    private static final String CHOICE = "NEEE|SSE(EE|N)";
    private static final String route = "^" + ELEMENT + "(" + CHOICE + ")$";

    @Test
    void next() {
        CompositeElementIterator parser = new CompositeElementIterator(Token.of(route));
        assertTrue(parser.hasNext());
        assertEquals(ELEMENT, parser.next().get());
        assertEquals(CHOICE, parser.next().get());
        assertFalse(parser.hasNext());
        assertThrows(NoSuchElementException.class, parser::next);
    }

    @Test
    void nextError() {
        final CompositeElementIterator parser = new CompositeElementIterator(Token.of("^(NEEE|SSE(EE|N)$"));
        assertTrue(parser.hasNext());
        assertThrows(IllegalStateException.class, parser::next);
    }

    @Test
    void of() {
        assertThrows(AssertionError.class, () -> new CompositeElementIterator(Token.of("NESW")));
        assertThrows(AssertionError.class, () -> new CompositeElementIterator(Token.of("^NESW")));
        assertThrows(AssertionError.class, () -> new CompositeElementIterator(Token.of("NESW$")));
        assertThrows(AssertionError.class, () -> new CompositeElementIterator(Token.of("NESW")));
        assertThrows(AssertionError.class, () -> new CompositeElementIterator(Token.of("^NESW$")));

        assertEquals("NESW|", new CompositeElementIterator(Token.of("(NESW|)")).toString());
    }
}