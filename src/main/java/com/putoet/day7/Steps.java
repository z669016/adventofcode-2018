package com.putoet.day7;

import java.util.*;
import java.util.stream.Collectors;

public class Steps {
    private final Map<String, Step> steps = new HashMap<>();

    public static Steps of(List<String> lines) {
        final Steps steps = new Steps();

        lines.stream()
                .map(Instruction::new)
                .forEach(instruction -> steps.get(instruction.after).after(steps.get(instruction.before)));

        return steps;
    }

    public Step get(String name) {
        name = name.toUpperCase();

        if (!steps.containsKey(name)) {
            steps.put(name, new Step(name));
        }

        return steps.get(name);
    }

    public Optional<Step> next(Set<Step> finished) {
        final Optional<Step> next = steps.values().stream()
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

    public void remove(Step step) {
        steps.remove(step.name());
    }

    public int size() {
        return steps.size();
    }
}
