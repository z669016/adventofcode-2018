package com.putoet.device;

import com.putoet.device.Instruction;
import com.putoet.device.Regs;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RegsTest {
    @Test
    void get() {
        final Regs regs = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        assertThat(regs.get(0)).isEqualTo(1);
        assertThat(regs.get(1)).isEqualTo(2);
        assertThat(regs.get(2)).isEqualTo(3);
        assertThat(regs.get(3)).isEqualTo(4);
    }

    @Test
    void set() {
        final Regs a = new Regs();
        final Regs b = a.set(0,1);

        assertThat(a.toString()).isEqualTo("[0, 0, 0, 0]");
        assertThat(b.toString()).isEqualTo("[1, 0, 0, 0]");
    }

    @Test
    void testEquals() {
        final Regs a = new Regs();
        final Regs b = a.set(0, 1);
        assertThat(a).isNotEqualTo(b);

        final Regs c = a.set(0, 1);
        assertThat(b).isEqualTo(c);
    }

    @Test
    void testToString() {
        final Regs regs = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        assertThat(regs.toString()).isEqualTo("[1, 2, 3, 4]");
    }

    @Test
    void apply() {
        final Instruction instruction = mock(Instruction.class);
        final Regs regs = new Regs();
        regs.apply(instruction);

        verify(instruction, times(1)).apply(regs);
    }
}