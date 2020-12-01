package com.putoet.day16;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class InstructionFactoryTest {
    private Map<Integer, String> opcodes;

    @BeforeEach
    void setup() {
        opcodes = new HashMap<>();
        opcodes.put(0, "addr");
        opcodes.put(1, "addi");
        opcodes.put(2, "mulr");
        opcodes.put(3, "muli");
        opcodes.put(4, "banr");
        opcodes.put(5, "bani");
        opcodes.put(6, "borr");
        opcodes.put(7, "bori");
        opcodes.put(8, "setr");
        opcodes.put(9, "seti");
        opcodes.put(10, "gtir");
        opcodes.put(11, "gtri");
        opcodes.put(12, "gtrr");
        opcodes.put(13, "eqir");
        opcodes.put(14, "eqri");
        opcodes.put(15, "eqrr");
    }

    @Test
    void of() {
        final InstructionFactory factory = new InstructionFactory(opcodes);
        for (int idx = 0; idx < 16; idx++) {
            assertThat(factory.of(idx, 0, 0, 0).name()).isEqualTo(opcodes.get(idx));
        }
    }
}