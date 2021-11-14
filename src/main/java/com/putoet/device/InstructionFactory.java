package com.putoet.device;

import java.util.HashMap;
import java.util.Map;

public class InstructionFactory {
    private static final Map<String, Long> OPCODES = new HashMap<>();

    static {
        OPCODES.put("addr", 0L);
        OPCODES.put("addi", 1L);
        OPCODES.put("mulr", 2L);
        OPCODES.put("muli", 3L);
        OPCODES.put("banr", 4L);
        OPCODES.put("bani", 5L);
        OPCODES.put("borr", 6L);
        OPCODES.put("bori", 7L);
        OPCODES.put("setr", 8L);
        OPCODES.put("seti", 9L);
        OPCODES.put("gtir", 10L);
        OPCODES.put("gtri", 11L);
        OPCODES.put("gtrr", 12L);
        OPCODES.put("eqir", 13L);
        OPCODES.put("eqri", 14L);
        OPCODES.put("eqrr", 15L);
    }

    private final Map<Long, String> opcodes;

    public InstructionFactory(Map<Long, String> opcodes) {
        // ensure opcodes are provided
        assert opcodes != null;

        // ensure it contains 16
        for (long idx = 0; idx < 16; idx++)
            assert opcodes.containsKey(idx);

        // ensure all are unique
        assert opcodes.values().stream().distinct().count() == opcodes.size();

        this.opcodes = opcodes;
    }

    public Instruction of(long[] codes) {
        assert codes != null && codes.length == 4;

        return of(codes[0], codes[1], codes[2], codes[3]);
    }

    public Instruction of(long opcode, long a, long b, long c) {
        if (!opcodes.containsKey(opcode))
            throw new IllegalArgumentException("Invalid opcode " + opcode + " for " + opcodes);

        final String name = opcodes.get(opcode);
        return of(name, opcode, a, b, c);
    }

    public static Instruction of(String name, long a, long b, long c) {
        if (!OPCODES.containsKey(name))
            throw new IllegalArgumentException("Invalid opcode " + name + " for " + OPCODES);

        final long opcode = OPCODES.get(name);
        return of(name, opcode, a, b, c);
    }

    private static Instruction of(String name, long opcode, long a, long b, long c) {
        return switch (name) {
            case "addr" -> Instruction.addr(opcode, a, b, c);
            case "addi" -> Instruction.addi(opcode, a, b, c);
            case "mulr" -> Instruction.mulr(opcode, a, b, c);
            case "muli" -> Instruction.muli(opcode, a, b, c);
            case "banr" -> Instruction.banr(opcode, a, b, c);
            case "bani" -> Instruction.bani(opcode, a, b, c);
            case "borr" -> Instruction.borr(opcode, a, b, c);
            case "bori" -> Instruction.bori(opcode, a, b, c);
            case "setr" -> Instruction.setr(opcode, a, b, c);
            case "seti" -> Instruction.seti(opcode, a, b, c);
            case "gtir" -> Instruction.gtir(opcode, a, b, c);
            case "gtri" -> Instruction.gtri(opcode, a, b, c);
            case "gtrr" -> Instruction.gtrr(opcode, a, b, c);
            case "eqir" -> Instruction.eqir(opcode, a, b, c);
            case "eqri" -> Instruction.eqri(opcode, a, b, c);
            case "eqrr" -> Instruction.eqrr(opcode, a, b, c);
            default -> throw new IllegalArgumentException("Undefined instruction " + name);
        };
    }
}
