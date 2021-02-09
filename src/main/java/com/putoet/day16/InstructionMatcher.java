package com.putoet.day16;

import com.putoet.device.Instruction;
import com.putoet.device.Regs;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InstructionMatcher {
    private static final Pattern BEFORE = Pattern.compile("Before: \\[(\\d), (\\d), (\\d), (\\d)\\]");
    private static final Pattern AFTER = Pattern.compile("After: +\\[(\\d), (\\d), (\\d), (\\d)\\]");
    private static final Pattern INSTR = Pattern.compile("(\\d+) (\\d) (\\d) (\\d)");

    public static Set<String> match(int[] b, int[] a, int[] i) {
        assert b.length == 4 && a.length == 4 && i.length == 4;

        final Regs before = new Regs(b);
        final Regs after = new Regs(a);

        final List<Instruction> instructionSet = Instruction.instructionSet(i[0], i[1], i[2], i[3]);
        return instructionSet.stream().sequential()
                .filter(instruction -> before.apply(instruction).equals(after))
                .map(Instruction::name)
                .collect(Collectors.toSet());
    }

    public static int[] before(String line) {
        final Matcher matcher = BEFORE.matcher(line);
        if (matcher.matches())
            return getInts(matcher);

        throw new IllegalArgumentException("Invalid before: " + line);
    }

    public static int[] after(String line) {
        final Matcher matcher = AFTER.matcher(line);
        if (matcher.matches())
            return getInts(matcher);

        throw new IllegalArgumentException("Invalid after: " + line);
    }

    public static int[] instruction(String line) {
        final Matcher matcher = INSTR.matcher(line);
        if (matcher.matches())
            return getInts(matcher);

        throw new IllegalArgumentException("Invalid instruction: " + line);
    }

    private static int[] getInts(Matcher matcher) {
        return new int[]{Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4))};
    }
}
