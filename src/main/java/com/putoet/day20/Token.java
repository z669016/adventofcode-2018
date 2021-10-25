package com.putoet.day20;

import java.util.function.Supplier;

public class Token implements Supplier<String> {
    public static final char START = '^';
    public static final char END = '$';
    public static final char OPEN = '(';
    public static final char CLOSE = ')';
    public static final char SEPARATOR = '|';

    private final String token;

    protected Token(String token) {
        this.token = token;
    }

    @Override
    public String get() {
        return token;
    }

    public static Token of(String token) {
        if (isRoute(token))
            return of(token.substring(1, token.length() - 1));

        if (isChoice(token))
            return new Choice(token);

        return  isComposite(token) ? new CompositeElement(token) : new Element(token);
    }

    public static boolean isRoute(String token) {
        return token.charAt(0) == START && token.charAt(token.length() - 1) == END;
    }

    public static boolean isChoice(String token) {
        return token.charAt(0) == OPEN && token.charAt(token.length() - 1) == CLOSE;
    }

    public static boolean isComposite(String token) {
        return token.indexOf(OPEN) != -1 || token.indexOf(CLOSE) != -1 || token.indexOf(SEPARATOR) != -1;
    }
}
