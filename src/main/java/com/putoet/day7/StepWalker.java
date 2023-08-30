package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

class StepWalker {
    private final Step step;
    private int walking;

    public StepWalker(@NotNull Step step, int delay) {
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
