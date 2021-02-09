package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GuardLogTest {
    private GuardLog log;

    @BeforeEach
    void setup() {
        log = GuardLog.of(ResourceLines.list("/day4.txt"));
    }

    @Test
    void of() {
        assertEquals(17, log.events().size());
    }

    @Test
    void guards() {
        assertEquals(2, log.guards().size());
        assertEquals(List.of(10, 99), log.guards().stream().map(g -> g.id).collect(Collectors.toList()));
    }

    @Test
    void shifts() {
        final Map<LocalDate,List<GuardEvent>> shifts = log.shifts(new Guard(99));
        assertEquals(3, shifts.size());
    }

    @Test
    void sleepTime() {
        assertEquals(30, log.sleepTime(new Guard(99)));
    }

    @Test
    void sleepMinute() {
        assertEquals(24, log.sleepMinute(new Guard(10)));
    }

    @Test
    void sliepiest() {
        assertEquals(10, log.longestSleeper().get().id);
    }

    @Test
    void mostFrequentSleeper() {
        assertEquals(99, log.mostFrequentSleeper().get().id);
    }
}