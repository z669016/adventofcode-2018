package com.putoet.day16;

import java.util.Map;

public class InstructionFactory {
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

        final String instruction = opcodes.get(opcode);
        return switch(instruction) {
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
            default -> throw new IllegalArgumentException("Undefined instruction " + instruction);
        };
    }
}
