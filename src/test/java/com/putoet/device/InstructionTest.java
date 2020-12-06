package com.putoet.device;

import com.putoet.device.Instruction;
import com.putoet.device.Regs;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InstructionTest {

    @Test
    void addr() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.addr(0, 1, 2, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(5);
        assertThat(instruction.name()).isEqualTo("addr");
    }

    @Test
    void addi() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.addi(0, 2, 4, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(7);
        assertThat(instruction.name()).isEqualTo("addi");
    }

    @Test
    void mulr() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.mulr(0, 1, 2, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(6);
        assertThat(instruction.name()).isEqualTo("mulr");
    }

    @Test
    void muli() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.muli(0, 2, 4, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(12);
        assertThat(instruction.name()).isEqualTo("muli");
    }

    @Test
    void banr() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.banr(0, 1, 2, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(2);
        assertThat(instruction.name()).isEqualTo("banr");
    }

    @Test
    void bani() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.bani(0, 2, 3, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(3);
        assertThat(instruction.name()).isEqualTo("bani");
    }

    @Test
    void borr() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.borr(0, 0, 1, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(3);
        assertThat(instruction.name()).isEqualTo("borr");
    }

    @Test
    void bori() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.bori(0, 1, 1, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(3);
        assertThat(instruction.name()).isEqualTo("bori");
    }

    @Test
    void setr() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.setr(0, 0, 1, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(1);
        assertThat(instruction.name()).isEqualTo("setr");
    }

    @Test
    void seti() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.seti(0, 7, 1, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(7);
        assertThat(instruction.name()).isEqualTo("seti");
    }

    @Test
    void gtir() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.gtir(0, 4, 3, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(0);
        assertThat(instruction.name()).isEqualTo("gtir");
    }

    @Test
    void gtri() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.gtri(0, 1, 1, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(1);
        assertThat(instruction.name()).isEqualTo("gtri");
    }

    @Test
    void gtrr() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.gtrr(0, 2, 1, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(1);
        assertThat(instruction.name()).isEqualTo("gtrr");
    }

    @Test
    void eqir() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.eqir(0, 4, 3, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(1);
        assertThat(instruction.name()).isEqualTo("eqir");
    }

    @Test
    void eqri() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.eqri(0, 1, 2, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(1);
        assertThat(instruction.name()).isEqualTo("eqri");
    }

    @Test
    void eqrr() {
        final Regs before = new Regs().set(0, 1).set(1, 2).set(2, 3).set(3, 4);
        final Instruction instruction = Instruction.eqrr(0, 2, 1, 3);
        final Regs after = before.apply(instruction);

        assertThat(after.get(3)).isEqualTo(0);
        assertThat(instruction.name()).isEqualTo("eqrr");
    }

    @Test
    void instructionSet() {
        final List<String> instructionSet = Instruction.instructionSet(0, 1, 2, 3).stream()
                .sequential()
                .map(Instruction::name)
                .collect(Collectors.toList());

        assertEquals(List.of("addr", "addi", "mulr", "muli", "banr", "bani", "borr", "bori", "setr", "seti", "gtir", "gtri", "gtrr", "eqir", "eqri", "eqrr"), instructionSet);
    }
}