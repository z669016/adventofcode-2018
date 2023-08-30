package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day4 {
    public static void main(String[] args) {
        final GuardLog log = GuardLog.of(ResourceLines.list("/day4.txt"));

        Timer.run(() -> part1(log));
        Timer.run(() -> part2(log));
    }

    private static void part1(GuardLog log) {
        final var sleepiest = log.longestSleeper().orElseThrow();
        final var minute = log.sleepMinute(sleepiest);
        System.out.println("the ID of the guard you chose multiplied by the minute is " + (sleepiest.id() * minute));
    }

    private static void part2(GuardLog log) {
        final var mostFrequent = log.mostFrequentSleeper().orElseThrow();
        final var minute = log.sleepMinute(mostFrequent);
        System.out.println("the ID of the guard most frequently sleeping multiplied by the minute is " + (mostFrequent.id() * minute));
    }
}
