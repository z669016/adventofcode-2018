package com.putoet.day16;

import com.putoet.device.Instruction;
import com.putoet.device.Regs;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class InstructionMatcher {
    private static final Pattern BEFORE = Pattern.compile("Before: \\[(\\d), (\\d), (\\d), (\\d)]");
    private static final Pattern AFTER = Pattern.compile("After: +\\[(\\d), (\\d), (\\d), (\\d)]");
    private static final Pattern INSTR = Pattern.compile("(\\d+) (\\d) (\\d) (\\d)");

    public static Set<String> match(long[] b, long[] a, long[] i) {
        assert b.length == 4 && a.length == 4 && i.length == 4;

        final var before = new Regs(b);
        final var after = new Regs(a);

        final var instructionSet = Instruction.instructionSet(i[0], i[1], i[2], i[3]);
        return instructionSet.stream()
                .filter(instruction -> before.apply(instruction).equals(after))
                .map(Instruction::name)
                .collect(Collectors.toSet());
    }

    public static long[] before(@NotNull String line) {
        final var matcher = BEFORE.matcher(line);
        if (matcher.matches())
            return getLongs(matcher);

        throw new IllegalArgumentException("Invalid before: " + line);
    }

    public static long[] after(@NotNull String line) {
        final var matcher = AFTER.matcher(line);
        if (matcher.matches())
            return getLongs(matcher);

        throw new IllegalArgumentException("Invalid after: " + line);
    }

    public static long[] instruction(@NotNull String line) {
        final var matcher = INSTR.matcher(line);
        if (matcher.matches())
            return getLongs(matcher);

        throw new IllegalArgumentException("Invalid instruction: " + line);
    }

    private static long[] getLongs(Matcher matcher) {
        return new long[]{
                Long.parseLong(matcher.group(1)),
                Long.parseLong(matcher.group(2)),
                Long.parseLong(matcher.group(3)),
                Long.parseLong(matcher.group(4))
        };
    }
}
