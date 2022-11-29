package com.putoet.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WordListAnalyzer {
    private final List<WordAnalyzer> analyzers;

    public WordListAnalyzer(List<WordAnalyzer> analyzers) {
        assert analyzers != null;

        this.analyzers = analyzers;
    }

    public static WordListAnalyzer of(List<String> words) {
        assert words != null;

        return new WordListAnalyzer(words.stream().map(WordAnalyzer::new).toList());
    }

    public int checksum() {
        final int doubles = analyzers.stream().mapToInt(a -> !a.doubles().isEmpty() ? 1 : 0).sum();
        final int triples = analyzers.stream().mapToInt(a -> !a.triples().isEmpty() ? 1 : 0).sum();

        return doubles * triples;
    }

    public List<String> oneLetterDifference() {
        final List<String> diffs = new ArrayList<>();
        for (int i = 0; i < analyzers.size() - 1; i++) {
            for (int o = i + 1; o < analyzers.size(); o++) {
                final Optional<String> diff = analyzers.get(i).oneLetterDifference(analyzers.get(o));
                diff.ifPresent(diffs::add);
            }
        }
        return diffs;
    }
}
