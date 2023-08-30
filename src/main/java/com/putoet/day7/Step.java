package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Step implements Comparable<Step> {
    private final String name;
    private final Set<Step> after = new HashSet<>();

    public Step(@NotNull String name) {
        assert name.length() == 1 && Character.isLetter(name.charAt(0));

        this.name = name.toUpperCase();
    }

    public String name() {
        return name;
    }

    public void after(@NotNull Step step) {
        after.add(step);
    }

    public int dependencyCount() {
        return after.size();
    }

    public boolean available(@NotNull Set<Step> finished) {
        return finished.containsAll(after);
    }

    @Override
    public int compareTo(@NotNull Step other) {
        return name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + " -> " + after.stream().map(Step::name).collect(Collectors.joining(",", "[", "]"));
    }
}
