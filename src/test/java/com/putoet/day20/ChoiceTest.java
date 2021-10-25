package com.putoet.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChoiceTest {
    public static final String CHOICE = "S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS)))";

    @Test
    void split() {
        String[] split = Choice.split(CHOICE);
        assertEquals(2, split.length);
        assertEquals("S", split[0]);
        assertEquals("NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS)))", split[1]);

        split = Choice.split("ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))|");
        assertEquals(3, split.length);
        assertEquals("ESSSSW(NWSW|SSEN)", split[0]);
        assertEquals("WSWWN(E|WWS(E|SS))", split[1]);
        assertEquals("", split[2]);
    }

    @Test
    void elementCount() {
        final Choice choice = new Choice("(" + CHOICE + ")");
        assertEquals(2, choice.elementCount());
    }
}