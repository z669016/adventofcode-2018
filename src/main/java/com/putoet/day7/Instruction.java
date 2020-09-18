package com.putoet.day7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instruction {
    private final Pattern patter = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.");

    public final String before;
    public final String after;

    public Instruction(String line) {
        assert line != null;

        final Matcher matcher = patter.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid instruction '" + line + "'");

        before = matcher.group(1);
        after = matcher.group(2);
    }
}
