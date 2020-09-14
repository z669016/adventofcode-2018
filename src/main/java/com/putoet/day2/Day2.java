package com.putoet.day2;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        final WordListAnalyzer analyzer = WordListAnalyzer.of(ResourceLines.list("/day2.txt"));
        System.out.println("Checksum is " + analyzer.checksum());

        final List<String> diffs = analyzer.oneLetterDifference();
        System.out.println("Words with one letter difference are " + diffs);
    }
}
