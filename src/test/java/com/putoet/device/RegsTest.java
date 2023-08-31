package com.putoet.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegsTest {
    private Regs regs;

    @BeforeEach
    void setup() {
        regs = new Regs();
        regs.set(0, 1);
        regs.set(1, 2);
        regs.set(2, 3);
        regs.set(3, 4);
    }

    @Test
    void get() {
        assertEquals(1, regs.get(0));
        assertEquals(2, regs.get(1));
        assertEquals(3, regs.get(2));
        assertEquals(4, regs.get(3));
    }

    @Test
    void set() {
        regs.set(0, 7);

        assertEquals("[7, 2, 3, 4]", regs.toString());
    }

    @Test
    void testToString() {
        assertEquals("[1, 2, 3, 4]", regs.toString());
    }

    @Test
    void apply() {
        final var instruction = mock(Instruction.class);
        final var regs = new Regs();
        regs.accept(instruction);

        verify(instruction, times(1)).accept(regs);
    }
}