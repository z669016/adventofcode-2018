package com.putoet.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Choice extends CompositeElement {

    private final Token[] tokens;

    public Choice(String token) {
        super(token.substring(1, token.length() - 1));

        assert Token.isChoice(token);
        final String[] parts = split(token.substring(1, token.length() - 2));

        tokens = new Token[parts.length];
        IntStream.range(0, parts.length).forEach(i -> tokens[i] = Token.of(parts[i]));
    }

    public static String[] split(String token) {
        final List<String> tokens = new ArrayList<>();
        int start = 0;
        int end = start + 1;
        int depth = 0;

        while (end < token.length()) {
            switch (token.charAt(end)) {
                case Token.OPEN -> depth++;
                case Token.CLOSE -> depth--;
                case Token.SEPARATOR -> {
                    if (depth == 0) {
                        tokens.add(token.substring(start, end));
                        start = ++end;
                    }
                }
            }

            end++;
        }
        tokens.add(token.substring(start));

        return tokens.toArray(new String[0]);
    }

    public int elementCount() {
        return tokens.length;
    }

    public Token get(int idx) {
        assert idx >= 0 && idx < tokens.length;
        return tokens[idx];
    }
}
