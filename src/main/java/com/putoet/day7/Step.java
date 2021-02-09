package com.putoet.day7;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Step implements Comparable<Step> {
    private final String name;
    private final Set<Step> after = new HashSet<>();

    public Step(String name) {
        assert name != null && name.length() == 1 && Character.isLetter(name.charAt(0));

        this.name = name.toUpperCase();
    }

    public String name() {
        return name;
    }

    public void after(Step step) {
        assert step != null;

        after.add(step);
    }

    public int dependencyCount() {
        return after.size();
    }

    public boolean available(Set<Step> finished) {
        return finished.containsAll(after);
    }

    @Override
    public int compareTo(Step other) {
        assert other != null;

        return name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + " -> " + after.stream().map(Step::name).collect(Collectors.joining(",", "[", "]"));
    }
}
