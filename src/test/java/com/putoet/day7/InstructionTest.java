package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new Instruction(null));
        assertThrows(IllegalArgumentException.class, () -> new Instruction("bla"));

        final Instruction instruction = new Instruction("Step C must be finished before step A can begin.");
        assertEquals("C", instruction.before);
        assertEquals("A", instruction.after);
    }
}