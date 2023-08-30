package com.putoet.day2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class WordListAnalyzer {
    private final List<WordAnalyzer> analyzers;

    public WordListAnalyzer(@NotNull List<WordAnalyzer> analyzers) {
        this.analyzers = analyzers;
    }

    public static WordListAnalyzer of(@NotNull List<String> words) {
        return new WordListAnalyzer(words.stream().map(WordAnalyzer::new).toList());
    }

    public int checksum() {
        final var doubles = analyzers.stream().mapToInt(a -> !a.doubles().isEmpty() ? 1 : 0).sum();
        final var triples = analyzers.stream().mapToInt(a -> !a.triples().isEmpty() ? 1 : 0).sum();

        return doubles * triples;
    }

    public List<String> oneLetterDifference() {
        final var diffs = new ArrayList<String>();
        for (var i = 0; i < analyzers.size() - 1; i++) {
            for (var o = i + 1; o < analyzers.size(); o++) {
                final var diff = analyzers.get(i).oneLetterDifference(analyzers.get(o));
                diff.ifPresent(diffs::add);
            }
        }
        return diffs;
    }
}
