package com.putoet.day16;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class InstructionMatcherTest {
    @Test
    void match() {
        final int[] before = {3, 2, 1, 1};
        final int[] after = {3, 2, 2, 1};
        final int[] instruction = {9, 2, 1, 2};

        assertThat(InstructionMatcher.match(before, after, instruction)).isEqualTo(Set.of("addi", "mulr", "seti"));
    }

    @Test
    void toArrayBefore() {
        assertThat(InstructionMatcher.before("Before: [1, 2, 3, 4]")).isEqualTo(new int[] {1, 2, 3, 4});
    }

    @Test
    void toArrayAfter() {
        assertThat(InstructionMatcher.after("After:  [1, 2, 3, 4]")).isEqualTo(new int[] {1, 2, 3, 4});
    }

    @Test
    void toArrayInstruction() {
        assertThat(InstructionMatcher.instruction("11 2 3 4")).isEqualTo(new int[] {11, 2, 3, 4});
    }
}