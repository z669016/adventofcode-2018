package com.putoet.device;

import java.util.Arrays;

public class Regs {
    private int[] regs;

    public Regs() {
        regs = new int[] {0, 0, 0, 0};
    }

    public Regs(int[] regs) {
        this.regs = new int[regs.length];
        System.arraycopy(regs, 0, this.regs, 0, regs.length);
    }

    public int get(int reg) {
        assert reg >=0 && reg < regs.length;
        return regs[reg];
    }

    public Regs set(int reg, int value) {
        assert reg >=0 && reg < regs.length;

        final Regs updated = new Regs(regs);
        updated.regs[reg] = value;

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
