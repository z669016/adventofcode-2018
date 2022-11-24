package com.putoet.day16;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InstructionMatcherTest {
    @Test
    void match() {
        final long[] before = {3, 2, 1, 1};
        final long[] after = {3, 2, 2, 1};
        final long[] instruction = {9, 2, 1, 2};

        assertEquals(InstructionMatcher.match(before, after, instruction), Set.of("addi", "mulr", "seti"));
    }

    @Test
    void toArrayBefore() {
        assertArrayEquals(InstructionMatcher.before("Before: [1, 2, 3, 4]"), new long[] {1, 2, 3, 4});
    }

    @Test
    void toArrayAfter() {
        assertArrayEquals(InstructionMatcher.after("After:  [1, 2, 3, 4]"), new long[] {1, 2, 3, 4});
    }

    @Test
    void toArrayInstruction() {
        assertArrayEquals(InstructionMatcher.instruction("11 2 3 4"), new long[] {11, 2, 3, 4});
    }
}