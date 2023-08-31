package com.putoet.device;

import java.util.Arrays;
import java.util.function.Function;

public record Regs(long[] regs) implements Function<Instruction,Regs> {

    public Regs() {
        this(new long[] {0, 0, 0, 0});
    }

    public Regs {
        final var copy = new long[regs.length];
        System.arraycopy(regs, 0, copy, 0, regs.length);

        regs = copy;
    }

    public long get(long reg) {
        assert reg >=0 && reg < regs.length;
        return regs[(int) reg];
    }

    public Regs set(long reg, long value) {
        assert reg >=0 && reg < regs.length;

        final var updated = new Regs(regs);
        updated.regs[(int) reg] = value;

        return updated;
    }

    public int size() {
        return regs.length;
    }

    @Override
    public Regs apply(Instruction instruction) {
        return instruction.apply(this);
    }

    @Override
    public String toString() {
        return Arrays.toString(regs);
    }
}
