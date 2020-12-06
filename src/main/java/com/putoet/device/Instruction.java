package com.putoet.device;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class Instruction {
    private final int opcode;
    private final int a;
    private final int b;
    private final int c;

    public int opcode() { return opcode; }
    public int a() { return a; }
    public int b() { return b; }
    public int c() { return c; }

    public abstract Regs apply(Regs regs);
    public abstract String name();

    @Override
    public String toString() {
        return String.format("%s %d %d %d", name(), a, b, c);
    }

    public static Instruction addr (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) + regs.get(b));
            }

            @Override
            public String name() {
                return "addr";
            }
        };
    }

    public static Instruction addi (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) + b);
            }

            @Override
            public String name() {
                return "addi";
            }
        };
    }

    public static Instruction mulr (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) * regs.get(b));
            }

            @Override
            public String name() {
                return "mulr";
            }
        };
    }

    public static Instruction muli (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) * b);
            }

            @Override
            public String name() {
                return "muli";
            }
        };
    }

    public static Instruction banr (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) & regs.get(b));
            }

            @Override
            public String name() {
                return "banr";
            }
        };
    }

    public static Instruction bani (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) & b);
            }

            @Override
            public String name() {
                return "bani";
            }
        };
    }

    public static Instruction borr (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) | regs.get(b));
            }

            @Override
            public String name() {
                return "borr";
            }
        };
    }

    public static Instruction bori (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) | b);
            }

            @Override
            public String name() {
                return "bori";
            }
        };
    }

    public static Instruction setr (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a));
            }

            @Override
            public String name() {
                return "setr";
            }
        };
    }

    public static Instruction seti (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, a);
            }

            @Override
            public String name() {
                return "seti";
            }
        };
    }

    public static Instruction gtir (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, a > regs.get(b) ? 1 : 0);
            }

            @Override
            public String name() {
                return "gtir";
            }
        };
    }

    public static Instruction gtri (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) > b ? 1 : 0);
            }

            @Override
            public String name() {
                return "gtri";
            }
        };
    }

    public static Instruction gtrr (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) > regs.get(b) ? 1 : 0);
            }

            @Override
            public String name() {
                return "gtrr";
            }
        };
    }

    public static Instruction eqir (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, a == regs.get(b) ? 1 : 0);
            }

            @Override
            public String name() {
                return "eqir";
            }
        };
    }

    public static Instruction eqri (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) == b ? 1 : 0);
            }

            @Override
            public String name() {
                return "eqri";
            }
        };
    }

    public static Instruction eqrr (int opcode, int a, int b, int c) {
        return new Instruction(opcode, a, b, c) {
            @Override
            public Regs apply(Regs regs) {
                return regs.set(c, regs.get(a) == regs.get(b) ? 1 : 0);
            }

            @Override
            public String name() {
                return "eqrr";
            }
        };
    }

    public static List<Instruction> instructionSet(int opcode, int a, int b, int c) {
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
