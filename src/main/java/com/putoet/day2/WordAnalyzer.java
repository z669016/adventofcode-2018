package com.putoet.day2;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class WordAnalyzer {
    private final String word;
    private final Map<Character, Integer> occurrences;

    public WordAnalyzer(String word) {
        assert word != null;

        this.word = word;
        occurrences = word.chars().boxed().collect(toMap(k -> (char) k.intValue(), v -> 1, Integer::sum));
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
                .collect(toList());
    }

    public Optional<String> oneLetterDifference(WordAnalyzer other) {
        assert other != null;

        if (word.length() != other.word.length())
            return Optional.empty();

        final StringBuilder sb = new StringBuilder();
        int difference = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != other.word.charAt(i)) {
                difference++;
                if (difference > 1)
                    return Optional.empty();
            } else {
                sb.append(word.charAt(i));
            }
        }
        return Optional.of(sb.toString());
    }
}
