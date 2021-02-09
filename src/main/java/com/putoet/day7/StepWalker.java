package com.putoet.day7;

import java.util.Optional;

public class StepWalker {
    public static final int MIN_DURATION = 60;

    private final Step step;
    private int walking;

    public StepWalker(Step step) {
        this(step, MIN_DURATION);
    }

    public StepWalker(Step step, int delay) {
        this.step = step;
        walking = 1 + delay + step.name().charAt(0) - 'A';
    }

    public int walking() {
        return walking;
    }

    public Optional<String> walk() {
        return --walking == 0 ? Optional.of(step.name()) : Optional.empty();
    }

    public Step step() {
        return step;
    }
}
