package com.putoet.device;

import java.util.HashMap;
import java.util.Map;

public class InstructionFactory {
    private static final Map<String, Integer> OPCODES = new HashMap<>();

    static {
        OPCODES.put("addr", 0);
        OPCODES.put("addi", 1);
        OPCODES.put("mulr", 2);
        OPCODES.put("muli", 3);
        OPCODES.put("banr", 4);
        OPCODES.put("bani", 5);
        OPCODES.put("borr", 6);
        OPCODES.put("bori", 7);
        OPCODES.put("setr", 8);
        OPCODES.put("seti", 9);
        OPCODES.put("gtir", 10);
        OPCODES.put("gtri", 11);
        OPCODES.put("gtrr", 12);
        OPCODES.put("eqir", 13);
        OPCODES.put("eqri", 14);
        OPCODES.put("eqrr", 15);
    }

    private final Map<Integer, String> opcodes;

    public InstructionFactory(Map<Integer, String> opcodes) {
        // ensure opcodes are provided
        assert opcodes != null;

        // ensure it contains 16
        for (int idx = 0; idx < 16; idx++)
            assert opcodes.containsKey(idx);

        // ensure all are unique
        assert opcodes.values().stream().distinct().count() == opcodes.size();

        this.opcodes = opcodes;
    }

    public Instruction of(int[] codes) {
        assert codes != null && codes.length == 4;

        return of(codes[0], codes[1], codes[2], codes[3]);
    }

    public Instruction of(int opcode, int a, int b, int c) {
        if (!opcodes.containsKey(opcode))
            throw new IllegalArgumentException("Invalid opcode " + opcode + " for " + opcodes);

        final String name = opcodes.get(opcode);
        return of(name, opcode, a, b, c);
    }

    public static Instruction of(String name, int a, int b, int c) {
        if (!OPCODES.containsKey(name))
            throw new IllegalArgumentException("Invalid opcode " + name + " for " + OPCODES);

        final int opcode = OPCODES.get(name);
        return of(name, opcode, a, b, c);
    }

    private static Instruction of(String name, int opcode, int a, int b, int c) {
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
