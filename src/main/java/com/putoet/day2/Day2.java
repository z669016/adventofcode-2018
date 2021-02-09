package com.putoet.day2;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        final WordListAnalyzer analyzer = WordListAnalyzer.of(ResourceLines.list("/day2.txt"));

        part1(analyzer);
        part2(analyzer);
    }

    private static void part1(WordListAnalyzer analyzer) {
        System.out.println("Checksum is " + analyzer.checksum());
    }

    private static void part2(WordListAnalyzer analyzer) {
        final List<String> diffs = analyzer.oneLetterDifference();
        System.out.println("Words with one letter difference are " + diffs);
    }
}
