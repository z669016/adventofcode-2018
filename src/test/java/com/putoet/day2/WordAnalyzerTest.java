package com.putoet.day2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordAnalyzerTest {
    public static final List<WordAnalyzer> ANALYZERS = List.of(
            new WordAnalyzer("abcdef"),
            new WordAnalyzer("bababc"),
            new WordAnalyzer("abbcde"),
            new WordAnalyzer("abcccd"),
            new WordAnalyzer("aabcdd"),
            new WordAnalyzer("abcdee"),
            new WordAnalyzer("ababab")
    );

    @Test
    void doubles() {
        assertEquals(0, ANALYZERS.get(0).triples().size());
        assertEquals(1, ANALYZERS.get(1).triples().size());
        assertEquals(0, ANALYZERS.get(2).triples().size());
        assertEquals(1, ANALYZERS.get(3).triples().size());
        assertEquals(0, ANALYZERS.get(4).triples().size());
        assertEquals(0, ANALYZERS.get(5).triples().size());
        assertEquals(2, ANALYZERS.get(6).triples().size());
    }

    @Test
    void triples() {
        assertEquals(0, ANALYZERS.get(0).doubles().size());
        assertEquals(1, ANALYZERS.get(1).doubles().size());
        assertEquals(1, ANALYZERS.get(2).doubles().size());
        assertEquals(0, ANALYZERS.get(3).doubles().size());
        assertEquals(2, ANALYZERS.get(4).doubles().size());
        assertEquals(1, ANALYZERS.get(5).doubles().size());
        assertEquals(0, ANALYZERS.get(6).doubles().size());
    }

    @Test
    void oneLetterDifference() {
        final var one = new WordAnalyzer("fghij");
        final var two = new WordAnalyzer("fguij");
        final var three = new WordAnalyzer("fguaa");

        final var diff = one.oneLetterDifference(two);
        assertTrue(diff.isPresent());
        assertEquals("fgij", diff.get());
        assertFalse(one.oneLetterDifference(three).isPresent());
        assertFalse(three.oneLetterDifference(three).isPresent());
    }
}