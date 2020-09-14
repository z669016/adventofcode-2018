package com.putoet.day4;

import com.putoet.resources.ResourceLines;

public class Day4 {
    public static void main(String[] args) {
        final GuardLog log = GuardLog.of(ResourceLines.list("/day4.txt"));

        final Guard sleepiest = log.longestSleeper();
        int minute = log.sleepMinute(sleepiest);
        System.out.println("the ID of the guard you chose multiplied by the minute is " + (sleepiest.id * minute));

        final Guard mostFrequent = log.mostFrequentSleeper();
        minute = log.sleepMinute(mostFrequent);
        System.out.println("the ID of the guard most frequently sleeping multiplied by the minute is " + (mostFrequent.id * minute));
    }
}
