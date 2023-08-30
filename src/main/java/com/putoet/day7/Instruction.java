package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

class Instruction {
    public final String before;
    public final String after;
    private static final Pattern patter = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.");

    public Instruction(@NotNull String line) {
        final var matcher = patter.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid instruction '" + line + "'");

        before = matcher.group(1);
        after = matcher.group(2);
    }
}
