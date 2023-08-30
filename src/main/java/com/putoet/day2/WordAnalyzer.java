package com.putoet.day2;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class WordAnalyzer {
    private final String word;
    private final Map<Character, Integer> occurrences;

    public WordAnalyzer(@NotNull String word) {
        this.word = word;
        occurrences = word.chars().boxed().collect(Collectors.toMap(k -> (char) k.intValue(), v -> 1, Integer::sum));
    }

    public List<Character> doubles() {
        return multiples(2);
    }

    public List<Character> triples() {
        return multiples(3);
    }

    private List<Character> multiples(int count) {
        return occurrences.entrySet().stream()
                .filter(e -> e.getValue() == count)
                .map(Map.Entry::getKey)
                .toList();
    }

    public Optional<String> oneLetterDifference(@NotNull WordAnalyzer other) {
        if (word.length() != other.word.length())
            return Optional.empty();

        final var sb = new StringBuilder();
        var difference = 0;
        for (var i = 0; i < word.length(); i++) {
            if (word.charAt(i) != other.word.charAt(i)) {
                difference++;
                if (difference > 1)
                    return Optional.empty();
            } else {
                sb.append(word.charAt(i));
            }
        }
        return difference == 0 ? Optional.empty() : Optional.of(sb.toString());
    }
}
