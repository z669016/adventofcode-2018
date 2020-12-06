package com.putoet.device;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Device {
    private final List<Instruction> program;
    private Regs regs;
    private int ip;
    private int ipRegister = -1;
    private boolean verbose = false;

    public Device(Regs regs, List<Instruction> program) {
        this.regs = regs;
        this.program = program;
    }

    public void enableVerbose() { verbose = true; }
    public void disableVerbose() { verbose = false; }

    public int register(int reg) {
        return regs.get(reg);
    }
    public void register(int reg, int value) {
        regs =  regs.set(reg, value);
    }

    public void ipRegister(int reg) {
        assert reg >=0 && reg <= regs.size();
        ipRegister = reg;
    }

    public void run() {
        ip = 0;
        while (ip >= 0 && ip < program.size()) {
            final Instruction instruction = program.get(ip);
            if (verbose) System.out.printf("ip=%d %s %s", ip, regs, instruction);

            storeIP();
            regs = regs.apply(instruction);
            retrieveIP();

            if (verbose) System.out.printf(" %s%n", regs);

            ip++;
        }
    }

    public List<Instruction> program() {
        return program;
    }

    private void storeIP() {
        if (ipRegister != -1)
            regs = regs.set(ipRegister, ip);
    }

    private void retrieveIP() {
        if (ipRegister != -1)
            ip = regs.get(ipRegister);
    }

    public static Device of(List<String> lines) {
        final Regs regs = new Regs(new int[6]);
        final List<Instruction> program = new ArrayList<>();
        final List<Declaration> declarations = new ArrayList<>();

        lines.forEach(line -> {
            if (line.startsWith("#"))
                declarations.add(parseDeclaration(line));
            else
                program.add(parseInstruction(line));
        });

        final Device device = new Device(regs, program);
        declarations.forEach(declaration -> declaration.apply(device));

        return device;
    }

    private static Declaration parseDeclaration(String line) {
        if (line.startsWith(("#ip "))) return Declaration.ip(line);

        throw new IllegalArgumentException("Invalid declaration '" + line + "'");
    }

    private static final Pattern PATTERN = Pattern.compile("([a-z]{4}) (\\d+) (\\d+) (\\d+)");
    private static Instruction parseInstruction(String line) {
        final Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid instruction '" + line + "'");

        final String name = matcher.group(1);
        final int a = Integer.parseInt(matcher.group(2));
        final int b = Integer.parseInt(matcher.group(3));
        final int c = Integer.parseInt(matcher.group(4));
        return InstructionFactory.of(name, a, b, c);
    }
}
