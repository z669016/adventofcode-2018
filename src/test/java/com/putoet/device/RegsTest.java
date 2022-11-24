package com.putoet.device;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegsTest {
    @Test
    void get() {
        final Regs regs = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        assertEquals(1, regs.get(0));
        assertEquals(2, regs.get(1));
        assertEquals(3, regs.get(2));
        assertEquals(4, regs.get(3));
    }

    @Test
    void set() {
        final Regs a = new Regs();
        final Regs b = a.set(0,1);

        assertEquals("[0, 0, 0, 0]", a.toString());
        assertEquals("[1, 0, 0, 0]", b.toString());
    }

    @Test
    void testEquals() {
        final Regs a = new Regs();
        final Regs b = a.set(0, 1);
        assertNotEquals(a, b);

        final Regs c = a.set(0, 1);
        assertEquals(b, c);
    }

    @Test
    void testToString() {
        final Regs regs = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        assertEquals("[1, 2, 3, 4]", regs.toString());
    }

    @Test
    void apply() {
        final Instruction instruction = mock(Instruction.class);
        final Regs regs = new Regs();
        regs.apply(instruction);

        verify(instruction, times(1)).apply(regs);
    }
}