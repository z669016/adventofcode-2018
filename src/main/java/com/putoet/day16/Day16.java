package com.putoet.day16;

import com.putoet.device.InstructionFactory;
import com.putoet.device.Regs;
import com.putoet.resources.ResourceLines;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        final List<String> lines = ResourceLines.list("/day16.txt");
        int threeOrMore = 0;

        for (int idx = 0; idx < lines.size(); idx += 4) {

            final Set<String> instructionSet = inStructionSet(lines, idx);
            if (instructionSet.size() >= 3)
                threeOrMore++;
        }

        System.out.println("how many samples in your puzzle input behave like three or more opcodes? " + threeOrMore);
    }

    private static void part2() {
        final List<String> opcodeLines = ResourceLines.list("/day16.txt");
        final Map<Integer, String> opcodes = opcodes(opcodeLines);
        final InstructionFactory factory = new InstructionFactory(opcodes);

        final List<String> instructions = ResourceLines.list("/day16-2.txt");
        Regs regs = new Regs();
        for (String line : instructions) {
            regs = regs.apply(factory.of(InstructionMatcher.instruction(line)));
        }

        System.out.println("The value in register 0 is " + regs.get(0));
    }

    private static Map<Integer, String> opcodes(List<String> lines) {
        final Map<Integer, Set<String>> opcodes = new HashMap<>();

        for (int idx = 0; idx < lines.size(); idx += 4) {
            int[] before = InstructionMatcher.before(lines.get(idx));
            int[] instruction = InstructionMatcher.instruction(lines.get(idx +1));
            int[] after = InstructionMatcher.after(lines.get(idx +2));

            final Set<String> instructionSet = InstructionMatcher.match(before, after, instruction);
            if (!opcodes.containsKey(instruction[0])) {
                opcodes.put(instruction[0], instructionSet);
            } else {
                opcodes.get(instruction[0]).retainAll(instructionSet);
            }
        }

        return reduce(opcodes);
    }

    private static Map<Integer, String> reduce(Map<Integer, Set<String>> opcodes) {
        Set<String> singles = singles(opcodes);
        while (singles.size() < 16) {
            for (Integer key : opcodes.keySet()) {
                final Set<String> values = opcodes.get(key);
                if (values.size() > 1)
                    values.removeAll(singles);
            }

            singles = singles(opcodes);
        }

        return opcodes.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().iterator().next()));
    }

    private static Set<String> singles(Map<Integer, Set<String>> opcodes) {
        Set<String> singles = opcodes.values().stream()
                .filter(value -> value.size() == 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        return singles;
    }

    private static Set<String> inStructionSet(List<String> lines, int idx) {
        int[] before = InstructionMatcher.before(lines.get(idx));
        int[] instruction = InstructionMatcher.instruction(lines.get(idx +1));
        int[] after = InstructionMatcher.after(lines.get(idx +2));

        return InstructionMatcher.match(before, after, instruction);
    }
}
