package com.putoet.device;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructionTest {

    @Test
    void addr() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.addr(0, 1, 2, 3);
        final var after = before.apply(instruction);

        assertEquals(5, after.get(3));
        assertEquals("addr", instruction.name());
    }

    @Test
    void addi() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.addi(0, 2, 4, 3);
        final var after = before.apply(instruction);

        assertEquals(7, after.get(3));
        assertEquals("addi", instruction.name());
    }

    @Test
    void mulr() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.mulr(0, 1, 2, 3);
        final var after = before.apply(instruction);

        assertEquals(6, after.get(3));
        assertEquals("mulr", instruction.name());
    }

    @Test
    void muli() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.muli(0, 2, 4, 3);
        final var after = before.apply(instruction);

        assertEquals(12, after.get(3));
        assertEquals("muli", instruction.name());
    }

    @Test
    void banr() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.banr(0, 1, 2, 3);
        final var after = before.apply(instruction);

        assertEquals(2, after.get(3));
        assertEquals("banr", instruction.name());
    }

    @Test
    void bani() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.bani(0, 2, 3, 3);
        final var after = before.apply(instruction);

        assertEquals(3, after.get(3));
        assertEquals("bani", instruction.name());
    }

    @Test
    void borr() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.borr(0, 0, 1, 3);
        final var after = before.apply(instruction);

        assertEquals(3, after.get(3));
        assertEquals("borr", instruction.name());
    }

    @Test
    void bori() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.bori(0, 1, 1, 3);
        final var after = before.apply(instruction);

        assertEquals(3, after.get(3));
        assertEquals("bori", instruction.name());
    }

    @Test
    void setr() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.setr(0, 0, 1, 3);
        final var after = before.apply(instruction);

        assertEquals(1, after.get(3));
        assertEquals("setr", instruction.name());
    }

    @Test
    void seti() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.seti(0, 7, 1, 3);
        final var after = before.apply(instruction);

        assertEquals(7, after.get(3));
        assertEquals("seti", instruction.name());
    }

    @Test
    void gtir() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.gtir(0, 4, 3, 3);
        final var after = before.apply(instruction);

        assertEquals(0, after.get(3));
        assertEquals("gtir", instruction.name());
    }

    @Test
    void gtri() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.gtri(0, 1, 1, 3);
        final var after = before.apply(instruction);

        assertEquals(1, after.get(3));
        assertEquals("gtri", instruction.name());
    }

    @Test
    void gtrr() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.gtrr(0, 2, 1, 3);
        final var after = before.apply(instruction);

        assertEquals(1, after.get(3));
        assertEquals("gtrr", instruction.name());
    }

    @Test
    void eqir() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.eqir(0, 4, 3, 3);
        final var after = before.apply(instruction);

        assertEquals(1, after.get(3));
        assertEquals("eqir", instruction.name());
    }

    @Test
    void eqri() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.eqri(0, 1, 2, 3);
        final var after = before.apply(instruction);

        assertEquals(1, after.get(3));
        assertEquals("eqri", instruction.name());
    }

    @Test
    void eqrr() {
        final var before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final var instruction = Instruction.eqrr(0, 2, 1, 3);
        final var after = before.apply(instruction);

        assertEquals(0, after.get(3));
        assertEquals("eqrr", instruction.name());
    }

    @Test
    void instructionSet() {
        final var instructionSet = Instruction.instructionSet(0, 1, 2, 3).stream()
                .map(Instruction::name)
                .toList();

        assertEquals(List.of("addr", "addi", "mulr", "muli", "banr", "bani", "borr", "bori", "setr", "seti", "gtir", "gtri", "gtrr", "eqir", "eqri", "eqrr"), instructionSet);
    }
}