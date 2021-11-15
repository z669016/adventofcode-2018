package com.putoet.device;

import java.util.function.Predicate;

public abstract class Debug implements Predicate<Device> {
    private boolean enabled = true;

    public boolean enabled() {
        return enabled;
    }

    public Debug enable() {
        enabled = true;
        return this;
    }

    public Debug disable() {
        enabled = false;
        return this;
    }
}
