package com.putoet.device;

import java.util.Arrays;

public class Regs {
    private long[] regs;

    public Regs() {
        regs = new long[] {0, 0, 0, 0};
    }

    public Regs(long[] regs) {
        this.regs = new long[regs.length];
        System.arraycopy(regs, 0, this.regs, 0, regs.length);
    }

    public long get(long reg) {
        assert reg >=0 && reg < regs.length;
        return regs[(int) reg];
    }

    public Regs set(long reg, long value) {
        assert reg >=0 && reg < regs.length;

        final Regs updated = new Regs(regs);
        updated.regs[(int) reg] = value;

        return updated;
    }

    public Regs apply(Instruction instruction) {
        return instruction.apply(this);
    }

    public int size() {
        return regs.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Regs)) return false;
        Regs regs1 = (Regs) o;
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
