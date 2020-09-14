package com.putoet.day4;

import java.util.Objects;

public class Guard {
    public final int id;

    public Guard(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Guard #" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guard)) return false;
        Guard guard = (Guard) o;
        return id == guard.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
