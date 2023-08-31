package com.putoet.device;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;

public class Device implements Runnable {
    private static final Pattern PATTERN = Pattern.compile("([a-z]{4}) (\\d+) (\\d+) (\\d+)");

    private final List<Instruction> program;
    private final Map<Long, List<Debug>> breakpoints = new HashMap<>();
    private final Regs regs;

    private long ip;
    private long ipRegister = -1;
    private Instruction instruction;

    public Device(Regs regs, List<Instruction> program) {
        this.regs = regs;
        this.program = program;
    }

    public static Device of(List<String> lines) {
        final var regs = new Regs(new long[6]);
        final var program = new ArrayList<Instruction>();
        final var declarations = new ArrayList<Declaration>();

        lines.forEach(line -> {
            if (line.startsWith("#"))
                declarations.add(parseDeclaration(line));
            else
                program.add(parseInstruction(line));
        });

        final var device = new Device(regs, program);
        declarations.forEach(declaration -> declaration.accept(device));

        return device;
    }

    private static Declaration parseDeclaration(String line) {
        if (line.startsWith(("#ip "))) return Declaration.ip(line);

        throw new IllegalArgumentException("Invalid declaration '" + line + "'");
    }

    private static Instruction parseInstruction(String line) {
        final var matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid instruction '" + line + "'");

        final var name = matcher.group(1);
        final var a = Integer.parseInt(matcher.group(2));
        final var b = Integer.parseInt(matcher.group(3));
        final var c = Integer.parseInt(matcher.group(4));
        return InstructionFactory.of(name, a, b, c);
    }

    public long register(long reg) {
        return regs.get(reg);
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

    @Override
    public void run() {
        ip = 0;
        storeIP();
        while (ip >= 0 && ip < program.size()) {
            instruction = program.get((int) ip);

            if (stopOnBreakpoint(ip))
                return;

            regs.accept(instruction);
            retrieveIP();

            ip++;
            storeIP();
        }
    }

    private boolean stopOnBreakpoint(long instructionPointer) {
        var debuggers = breakpoints.get(-1L);
        if (debuggers != null)
            for (var debug : debuggers) {
                if (!debug.test(this))
                    return true;
            }

        debuggers = breakpoints.get(instructionPointer + 1);
        if (debuggers != null)
            for (var debug : debuggers) {
                if (!debug.test(this))
                    return true;
            }

        return false;
    }

    public void addBreakpoint(long instructionPointer, @NotNull Debug debug) {
        assert instructionPointer >= -1 && instructionPointer < program.size();

        breakpoints.computeIfAbsent(instructionPointer + 1, i -> new ArrayList<>()).add(debug);
    }

    public List<Instruction> program() {
        return program;
    }

    private void storeIP() {
        if (ipRegister != -1)
            regs.set(ipRegister, ip);
    }

    private void retrieveIP() {
        if (ipRegister != -1)
            ip = regs.get(ipRegister);
    }
}
