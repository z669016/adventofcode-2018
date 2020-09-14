package com.putoet.day4;

import java.time.LocalDateTime;

public class GuardEvent implements Comparable<GuardEvent> {
    private final LocalDateTime dateTime;
    private final WatchEVent event;
    private Guard guard;

    public GuardEvent(LocalDateTime dateTime, WatchEVent event) {
        assert dateTime != null;

        this.dateTime = dateTime;
        this.event = event;
    }

    public GuardEvent(LocalDateTime dateTime, Guard guard) {
        assert dateTime != null;
        assert guard != null;

        this.dateTime = dateTime;
        this.guard = guard;
        this.event = WatchEVent.START_WATCH;
    }

    public LocalDateTime dateTime() { return dateTime; }
    public Guard guard() { return guard; }
    public WatchEVent event() { return event; }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    @Override
    public int compareTo(GuardEvent other) {
        return dateTime.compareTo(other.dateTime);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s", dateTime, guard, event);
    }
}
