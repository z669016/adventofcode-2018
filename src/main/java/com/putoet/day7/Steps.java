package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class Steps {
    private final Map<String, Step> steps = new HashMap<>();

    public static Steps of(@NotNull List<String> lines) {
        final var steps = new Steps();

        lines.stream()
                .map(Instruction::new)
                .forEach(instruction -> steps.get(instruction.after).after(steps.get(instruction.before)));

        return steps;
    }

    public Step get(@NotNull String name) {
        return steps.computeIfAbsent(name.toUpperCase(), Step::new);
    }

    public Optional<Step> next(@NotNull Set<Step> finished) {
        final var next = steps.values().stream()
                .filter(step -> step.available(finished))
                .sorted()
                .findFirst();

        next.ifPresent(step -> steps.remove(step.name()));

        return next;
    }

    public Set<Step> options(Set<Step> finished) {
        return steps.values().stream()
                .filter(step -> step.available(finished))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public void remove(@NotNull Step step) {
        steps.remove(step.name());
    }

    public int size() {
        return steps.size();
    }
}
