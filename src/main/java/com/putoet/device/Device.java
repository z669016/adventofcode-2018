package com.putoet.device;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Device {
    private static final Pattern PATTERN = Pattern.compile("([a-z]{4}) (\\d+) (\\d+) (\\d+)");
    private final List<Instruction> program;
    private final Map<Long, List<Debug>> breakpoints = new HashMap<>();
    private Regs regs;
    private long ip;
    private long ipRegister = -1;
    private Instruction instruction;

    public Device(Regs regs, List<Instruction> program) {
        this.regs = regs;
        this.program = program;
    }

    public static Device of(List<String> lines) {
        final Regs regs = new Regs(new long[6]);
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

    public long register(long reg) {
        return regs.get(reg);
    }

    public void register(long reg, long value) {
        regs = regs.set(reg, value);
    }

    public void ipRegister(long reg) {
        assert reg >= 0 && reg <= regs.size();
        ipRegister = reg;
    }

    public long ip() {
        return ip;
    }

    public Instruction instruction() {
        return instruction;
    }

    public Regs regs() {
        return regs;
    }

    public void run() {
        ip = 0;
        storeIP();
        while (ip >= 0 && ip < program.size()) {
            instruction = program.get((int) ip);

            if (!breakpoints(ip))
                return;

            regs = regs.apply(instruction);
            retrieveIP();

            ip++;
            storeIP();
        }
    }

    private boolean breakpoints(long instructionPointer) {
        // Breakpoint -1 must be performed on all instructions
        if (instructionPointer != -1 && !breakpoints(-1))
            return false;

        // Breakpoint on
        final List<Debug> debuggers = breakpoints.getOrDefault(instructionPointer + 1, Collections.emptyList());
        for (Debug debug : debuggers) {
            if (debug.enabled() && !debug.test(this))
                return false;
        }

        return true;
    }

    public Device addBreakpoint(long instructionPointer, Debug debug) {
        assert instructionPointer >= -1 && instructionPointer < program.size();

        final List<Debug> breakpointsOnLine = breakpoints.getOrDefault(instructionPointer + 1, new ArrayList<>());
        breakpointsOnLine.add(debug);
        breakpoints.put(instructionPointer + 1,breakpointsOnLine);

        return this;
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
}
