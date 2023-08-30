package com.putoet.day2;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordListAnalyzerTest {
    @Test
    void checksum() {
        final var analyzer = new WordListAnalyzer(WordAnalyzerTest.ANALYZERS);
        assertEquals(12, analyzer.checksum());
    }

    @Test
    void oneLetterDifference() {
        final var analyzer = WordListAnalyzer.of(ResourceLines.list("/day2.txt"));
        final var diffs = analyzer.oneLetterDifference();

        assertEquals(1, diffs.size());
        assertEquals("fgij", diffs.get(0));
    }
}