package com.putoet.day4;

import com.putoet.resources.ResourceLines;

import java.util.Optional;

public class Day4 {
    public static void main(String[] args) {
        final GuardLog log = GuardLog.of(ResourceLines.list("/day4.txt"));

        part1(log);
        part2(log);
    }

    private static void part1(GuardLog log) {
        final Optional<Guard> sleepiest = log.longestSleeper();
        if (sleepiest.isEmpty()) {
            System.out.println("No longest sleeping guard found");
            return;
        }

        long minute = log.sleepMinute(sleepiest.get());
        System.out.println("the ID of the guard you chose multiplied by the minute is " + (sleepiest.get().id * minute));
    }

    private static void part2(GuardLog log) {
        final Optional<Guard> mostFrequent = log.mostFrequentSleeper();
        if (mostFrequent.isEmpty()) {
            System.out.println("No most frequent sleeping guard found");
            return;
        }

        long minute = log.sleepMinute(mostFrequent.get());
        System.out.println("the ID of the guard most frequently sleeping multiplied by the minute is " + (mostFrequent.get().id * minute));
    }
}
