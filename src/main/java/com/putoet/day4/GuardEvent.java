package com.putoet.day4;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

class GuardEvent implements Comparable<GuardEvent> {
    private final LocalDateTime dateTime;
    private final WatchEvent event;
    private Guard guard;

    public GuardEvent(@NotNull LocalDateTime dateTime, @NotNull WatchEvent event) {
        this.dateTime = dateTime;
        this.event = event;
    }

    public GuardEvent(@NotNull LocalDateTime dateTime, @NotNull Guard guard) {
        this.dateTime = dateTime;
        this.guard = guard;
        this.event = WatchEvent.START_WATCH;
    }

    public LocalDateTime dateTime() {
        return dateTime;
    }

    public Guard guard() {
        return guard;
    }

    public WatchEvent event() {
        return event;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    @Override
    public int compareTo(@NotNull GuardEvent other) {
        return dateTime.compareTo(other.dateTime);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s", dateTime, guard, event);
    }
}
