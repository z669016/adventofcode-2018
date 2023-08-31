package com.putoet.device;

import java.util.Arrays;
import java.util.function.Consumer;

public class Regs implements Consumer<Instruction> {
    private final long[] regs;

    public Regs() {
        this.regs = new long[] {0, 0, 0, 0};
    }

    public Regs(long[] regs) {
        this.regs = Arrays.copyOf(regs, regs.length);
    }

    public long get(long reg) {
        assert reg >=0 && reg < regs.length;
        return regs[(int) reg];
    }

    public void set(long reg, long value) {
        assert reg >=0 && reg < regs.length;

        regs[(int) reg] = value;
    }

    public int size() {
        return regs.length;
    }

    @Override
    public void accept(Instruction instruction) {
        instruction.accept(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Regs regs1)) return false;
        return Arrays.equals(regs, regs1.regs);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(regs);
    }

    @Override
    public String toString() {
        return Arrays.toString(regs);
    }
}
