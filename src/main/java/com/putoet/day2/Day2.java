package com.putoet.day2;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day2 {
    public static void main(String[] args) {
        final var analyzer = WordListAnalyzer.of(ResourceLines.list("/day2.txt"));

        Timer.run(() -> part1(analyzer));
        Timer.run(() -> part2(analyzer));
    }

    private static void part1(WordListAnalyzer analyzer) {
        System.out.println("Checksum is " + analyzer.checksum());
    }

    private static void part2(WordListAnalyzer analyzer) {
        final var diffs = analyzer.oneLetterDifference();
        System.out.println("Words with one letter difference are " + diffs);
    }
}
