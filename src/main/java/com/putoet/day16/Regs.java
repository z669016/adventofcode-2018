package com.putoet.day16;

import java.util.Arrays;

public class Regs {
    private final int[] regs = {0, 0, 0, 0};

    public Regs() {}
    public Regs(int[] regs) {
        super();

        assert regs.length == this.regs.length;
        for (int idx = 0; idx < regs.length; idx++)
            this.regs[idx] = regs[idx];
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
