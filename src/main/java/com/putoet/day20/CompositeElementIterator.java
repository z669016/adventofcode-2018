package com.putoet.day20;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CompositeElementIterator implements Iterator<Token> {
    private final CompositeElement element;
    private final String route;
    private int ptr = 0;

    public CompositeElementIterator(Token compositeElement) {
        assert compositeElement instanceof CompositeElement;

        this.element = (CompositeElement) compositeElement;
        this.route = compositeElement.get();
    }

    @Override
    public boolean hasNext() {
        return ptr < route.length();
    }

    @Override
    public Token next() {
        if (!hasNext())
            throw new NoSuchElementException();

        final int end = (route.charAt(ptr) == Token.OPEN) ? findMatchingClose() : findStartOfNextElement();
        final String token = route.substring(ptr, end);
        ptr = end;
        return Token.of(token);
    }

    private int findStartOfNextElement() {
        assert route.charAt(ptr) != Token.OPEN;

        int end = ptr + 1;
        while (route.charAt(end) != Token.OPEN) {
            if (route.charAt(end) == Token.CLOSE || route.charAt(end) == Token.SEPARATOR)
                throw new IllegalStateException("Invalid element in route starting at position " + ptr);

            end++;
        }

        return end;
    }

    private int findMatchingClose() {
        assert route.charAt(ptr) == Token.OPEN;

        int end = ptr + 1;
        int depth = 0;
        while (!(route.charAt(end) == Token.CLOSE && depth == 0) && end < route.length() - 1) {
            if (route.charAt(end) == Token.OPEN)
                depth++;
            if (route.charAt(end) == Token.CLOSE)
                depth--;
            end++;
        }

        if (depth != 0)
            throw new IllegalStateException("Invalid choice in route starting at position " + ptr);

        return ++end;
    }

    @Override
    public String toString() {
        return route;
    }
}
