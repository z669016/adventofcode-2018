package com.putoet.day2;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordListAnalyzerTest {
    @Test
    void create() {
        assertThrows(AssertionError.class, () -> new WordListAnalyzer(null));
        assertThrows(AssertionError.class, () -> WordListAnalyzer.of(null));
    }

    @Test
    void checksum() {
        final WordListAnalyzer analyzer = new WordListAnalyzer(WordAnalyzerTest.ANALYZERS);
        assertEquals(12, analyzer.checksum());
    }

    @Test
    void oneLetterDifference() {
        final WordListAnalyzer analyzer = WordListAnalyzer.of(ResourceLines.list("/day2.txt"));
        final List<String> diffs = analyzer.oneLetterDifference();

        assertEquals(1, diffs.size());
        assertEquals("fgij", diffs.get(0));
    }
}