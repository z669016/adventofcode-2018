package com.putoet.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstructionFactoryTest {
    private Map<Long, String> opcodes;

    @BeforeEach
    void setup() {
        opcodes = new HashMap<>();
        opcodes.put(0L, "addr");
        opcodes.put(1L, "addi");
        opcodes.put(2L, "mulr");
        opcodes.put(3L, "muli");
        opcodes.put(4L, "banr");
        opcodes.put(5L, "bani");
        opcodes.put(6L, "borr");
        opcodes.put(7L, "bori");
        opcodes.put(8L, "setr");
        opcodes.put(9L, "seti");
        opcodes.put(10L, "gtir");
        opcodes.put(11L, "gtri");
        opcodes.put(12L, "gtrr");
        opcodes.put(13L, "eqir");
        opcodes.put(14L, "eqri");
        opcodes.put(15L, "eqrr");
    }

    @Test
    void of() {
        final var factory = new InstructionFactory(opcodes);
        for (var idx = 0L; idx < 16; idx++) {
            assertEquals(opcodes.get(idx), factory.of(idx, 0, 0, 0).name());
        }
    }
}