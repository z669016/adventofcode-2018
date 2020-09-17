package com.putoet.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolymersTest {

    @Test
    void react() {
        assertEquals("", Polymers.react("aA"));
        assertEquals("", Polymers.react("aBbA"));
        assertEquals("aBAb", Polymers.react("aBAb"));
        assertEquals("aabAAB", Polymers.react("aabAAB"));
        assertEquals("dabCBAcaDA", Polymers.react("dabAcCaCBAcCcaDA"));
    }

    @Test
    void units() {
        assertEquals("ab", Polymers.units("aBAb"));
        assertEquals("abcd", Polymers.units("dabCBAcaDA"));
    }

    @Test
    void reactWithout() {
        assertEquals(6, Polymers.reactWithout("dabAcCaCBAcCcaDA", 'a').length());
        assertEquals(8, Polymers.reactWithout("dabAcCaCBAcCcaDA", 'b').length());
        assertEquals(4, Polymers.reactWithout("dabAcCaCBAcCcaDA", 'c').length());
        assertEquals(6, Polymers.reactWithout("dabAcCaCBAcCcaDA", 'D').length());
    }
}