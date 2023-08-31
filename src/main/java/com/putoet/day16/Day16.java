package com.putoet.day16;

import com.putoet.device.InstructionFactory;
import com.putoet.device.Regs;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {
    public static void main(String[] args) {
        final var opcodeLines = ResourceLines.list("/day16.txt");
        Timer.run(() -> part1(opcodeLines));
        Timer.run(() -> part2(opcodeLines));
    }

    private static void part1(List<String> opcodeLines) {
        var threeOrMore = 0;

        for (var idx = 0; idx < opcodeLines.size(); idx += 4) {
            final var instructionSet = inStructionSet(opcodeLines, idx);
            if (instructionSet.size() >= 3)
                threeOrMore++;
        }

        System.out.println("how many samples in your puzzle input behave like three or more opcodes? " + threeOrMore);
    }

    private static void part2(List<String> opcodeLines) {
        final var opcodes = opcodes(opcodeLines);
        final var factory = new InstructionFactory(opcodes);

        final var instructions = ResourceLines.list("/day16-2.txt");
        final var regs = new Regs();
        for (var line : instructions) {
            regs.accept(factory.of(InstructionMatcher.instruction(line)));
        }

        System.out.println("The value in register 0 is " + regs.get(0));
    }

    private static Map<Long, String> opcodes(List<String> lines) {
        final var opcodes = new HashMap<Long, Set<String>>();

        for (var idx = 0; idx < lines.size(); idx += 4) {
            final var instruction = InstructionMatcher.instruction(lines.get(idx + 1));

            final var instructionSet = inStructionSet(lines, idx);
            if (!opcodes.containsKey(instruction[0])) {
                opcodes.put(instruction[0], instructionSet);
            } else {
                opcodes.get(instruction[0]).retainAll(instructionSet);
            }
        }

        return reduce(opcodes);
    }

    private static Map<Long, String> reduce(Map<Long, Set<String>> opcodes) {
        var singles = singles(opcodes);
        while (singles.size() < 16) {
            for (var key : opcodes.keySet()) {
                final var values = opcodes.get(key);
                if (values.size() > 1)
                    values.removeAll(singles);
            }

            singles = singles(opcodes);
        }

        return opcodes.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().iterator().next()));
    }

    private static Set<String> singles(Map<Long, Set<String>> opcodes) {
        return opcodes.values().stream()
                .filter(value -> value.size() == 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private static Set<String> inStructionSet(List<String> lines, int idx) {
        final var before = InstructionMatcher.before(lines.get(idx));
        final var instruction = InstructionMatcher.instruction(lines.get(idx + 1));
        final var after = InstructionMatcher.after(lines.get(idx + 2));

        return InstructionMatcher.match(before, after, instruction);
    }
}
