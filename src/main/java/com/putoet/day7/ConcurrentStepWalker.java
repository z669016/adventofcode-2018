package com.putoet.day7;


import org.jetbrains.annotations.NotNull;

import java.util.*;

class ConcurrentStepWalker {
    private final Steps steps;
    private final int concurrent;
    private int duration = 0;

    public ConcurrentStepWalker(@NotNull Steps steps) {
        this(steps, 1);
    }

    public ConcurrentStepWalker(@NotNull Steps steps, int concurrent) {
        this.steps = steps;
        this.concurrent = concurrent;
    }

    public String walk() {
        return concurrent == 1 ? walkOne() : walkMultiple();
    }

    public String walkMultiple() {
        return walkMultiple(0);
    }

    public String walkMultiple(int delay) {
        duration = 0;

        final var sb = new StringBuilder();
        final var walkers = new ArrayList<StepWalker>();
        final var finished = new TreeSet<Step>();

        var options = steps.options(finished);
        do {
            for (var walker : walkers) {
                final var letter = walker.walk();
                if (letter.isPresent()) {
                    finished.add(walker.step());
                    sb.append(letter.get());
                }
            }
            walkers.removeIf(walker -> walker.walking() == 0);

            options = steps.options(finished);
            final var iter = options.iterator();
            while (iter.hasNext() && walkers.size() < concurrent) {
                final Step step = iter.next();
                walkers.add(new StepWalker(step, delay));
                steps.remove(step);
            }

            if (!walkers.isEmpty())
                duration++;

        } while (!walkers.isEmpty());

        return sb.toString();
    }

    public String walkOne() {
        final var sb = new StringBuilder();
        final var finished = new TreeSet<Step>();

        for (var next = steps.next(finished); next.isPresent(); next = steps.next(finished)) {
            duration++;
            sb.append(next.get().name());
            finished.add(next.get());
        }

        return sb.toString();
    }

    public int duration() {
        return duration;
    }
}
