package com.putoet.day14;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

class RecipeScores {
    private final CircularList<Integer> array;

    public RecipeScores(int initialSize, @NotNull List<Integer> initialValues) {
        array = new CircularList<>(initialSize + 100);
        array.addAll(initialValues);
    }

    public RecipeScores bake(int count) {
        assert count > 0;

        final var elvesIndex = new int[] {0, 1};
        while (count > array.size() - 2) {
            bake(elvesIndex);
        }
        return this;
    }

    public RecipeScores bake(String code) {
        assert !code.isEmpty();

        final var digits = code.chars().map(i -> i - '0').toArray();
        final var elvesIndex = new int[] {0, 1};
        while (array.size() < digits.length + 1)
            bake(elvesIndex);

        while (!endsWith(digits,0) && !endsWith(digits,1)) {
            bake(elvesIndex);
        }
        return this;
    }

    public boolean endsWith(String code) {
        final var digits = code.chars().map(i -> i - '0').toArray();
        return endsWith(digits, 0);
    }

    private boolean endsWith(int[] digits, int offset) {
        for (var i = 0; i < digits.length; i++) {
            if (array.get(array.size() - digits.length - offset + i) != digits[i])
                return false;
        }

        return true;
    }

    private void bake(int[] elvesIndex) {
        final var currentScores = new int[] {array.get(elvesIndex[0]), array.get(elvesIndex[1])};
        final var score = currentScores[0] + currentScores[1];

        array.add(score > 9 ? score / 10 : score % 10);
        if (score > 9)
            array.add(score % 10);

        elvesIndex[0] = (elvesIndex[0] + 1 + currentScores[0]) % array.size();
        elvesIndex[1] = (elvesIndex[1] + 1 + currentScores[1]) % array.size();
    }


    public String skipAndLimit(int skip, int limit) {
        assert skip + limit <= array.size();

        return array.stream().skip(skip).limit(limit).map(String::valueOf).collect(Collectors.joining());
    }

    public int size() { return array.size(); }

    public String toString(int[] elves) {
        final var sb = new StringBuilder();
        for (var i = 0; i < array.size(); i++) {

            if (i == elves[0] % array.size()) sb.append('(');
            else if (i == elves[1] % array.size()) sb.append('[');
            else sb.append(' ');

            sb.append(array.get(i));

            if (i == elves[0] % array.size()) sb.append(')');
            else if (i == elves[1] % array.size()) sb.append(']');
            else sb.append(' ');
        }

        return sb.toString();
    }

    public List<Integer> asList() { return array; }

    public String toString() {
        return array.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
