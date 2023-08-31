package com.putoet.device;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
public abstract class Instruction implements Consumer<Regs> {
    private final long opcode;
    private final long a;
    private final long b;
    private final long c;

    @SuppressWarnings("unused")
    public long opcode() { return opcode; }
    public long a() { return a; }
    public long b() { return b; }
    public long c() { return c; }

    public abstract String name();

    @Override
    public String toString() {
        return String.format("%s %d %d %d", name(), a, b, c);
    }

    public static Instruction addr (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) + regs.get(b));
            }

            @Override
            public String name() {
                return "addr";
            }
        };
    }

    public static Instruction addi (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) + b);
            }

            @Override
            public String name() {
                return "addi";
            }
        };
    }

    public static Instruction mulr (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) * regs.get(b));
            }

            @Override
            public String name() {
                return "mulr";
            }
        };
    }

    public static Instruction muli (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) * b);
            }

            @Override
            public String name() {
                return "muli";
            }
        };
    }

    public static Instruction banr (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) & regs.get(b));
            }

            @Override
            public String name() {
                return "banr";
            }
        };
    }

    public static Instruction bani (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) & b);
            }

            @Override
            public String name() {
                return "bani";
            }
        };
    }

    public static Instruction borr (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) | regs.get(b));
            }

            @Override
            public String name() {
                return "borr";
            }
        };
    }

    public static Instruction bori (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) | b);
            }

            @Override
            public String name() {
                return "bori";
            }
        };
    }

    public static Instruction setr (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a));
            }

            @Override
            public String name() {
                return "setr";
            }
        };
    }

    public static Instruction seti (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, a);
            }

            @Override
            public String name() {
                return "seti";
            }
        };
    }

    public static Instruction gtir (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, a > regs.get(b) ? 1 : 0);
            }

            @Override
            public String name() {
                return "gtir";
            }
        };
    }

    public static Instruction gtri (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) > b ? 1 : 0);
            }

            @Override
            public String name() {
                return "gtri";
            }
        };
    }

    public static Instruction gtrr (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) > regs.get(b) ? 1 : 0);
            }

            @Override
            public String name() {
                return "gtrr";
            }
        };
    }

    public static Instruction eqir (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, a == regs.get(b) ? 1 : 0);
            }

            @Override
            public String name() {
                return "eqir";
            }
        };
    }

    public static Instruction eqri (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) == b ? 1 : 0);
            }

            @Override
            public String name() {
                return "eqri";
            }
        };
    }

    public static Instruction eqrr (long opcode, long a, long b, long c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public void accept(Regs regs) {
                regs.set(c, regs.get(a) == regs.get(b) ? 1 : 0);
            }

            @Override
            public String name() {
                return "eqrr";
            }
        };
    }

    public static List<Instruction> instructionSet(long opcode, long a, long b, long c) {
        return List.of(
                addr(opcode, a, b, c),
                addi(opcode, a, b, c),
                mulr(opcode, a, b, c),
                muli(opcode, a, b, c),
                banr(opcode, a, b, c),
                bani(opcode, a, b, c),
                borr(opcode, a, b, c),
                bori(opcode, a, b, c),
                setr(opcode, a, b, c),
                seti(opcode, a, b, c),
                gtir(opcode, a, b, c),
                gtri(opcode, a, b, c),
                gtrr(opcode, a, b, c),
                eqir(opcode, a, b, c),
                eqri(opcode, a, b, c),
                eqrr(opcode, a, b, c)
        );
    }
}
