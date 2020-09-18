package com.putoet.day7;


import java.util.*;

public class ConcurrentStepWalker {
    private final Steps steps;
    private final int concurrent;
    private int duration = 0;

    public ConcurrentStepWalker(Steps steps) {
        this(steps, 1);
    }

    public ConcurrentStepWalker(Steps steps, int concurrent) {
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

        final StringBuilder sb = new StringBuilder();
        final List<StepWalker> walkers = new ArrayList<>();
        final Set<Step> finished = new TreeSet<>();

        Set<Step> options = steps.options(finished);
        do {
            for (StepWalker walker : walkers) {
                final Optional<String> letter = walker.walk();
                if (letter.isPresent()) {
                    finished.add(walker.step());
                    sb.append(letter.get());
                }
            }
            walkers.removeIf(walker -> walker.walking() == 0);

            options = steps.options(finished);
            final Iterator<Step> iter = options.iterator();
            while (iter.hasNext() && walkers.size() < concurrent) {
                final Step step = iter.next();
                walkers.add(new StepWalker(step, delay));
                steps.remove(step);
            }

            if (walkers.size() > 0)
                duration++;

        } while (walkers.size() > 0);

        return sb.toString();
    }

    public String walkOne() {
        final StringBuilder sb = new StringBuilder();
        final Set<Step> finished = new TreeSet<>();

        for (Optional<Step> next = steps.next(finished); next.isPresent(); next = steps.next(finished)) {
            duration++;
            sb.append(next.get().name());
            finished.add(next.get());
        }

        return sb.toString();
    }

    public int duration() { return duration; }
}
