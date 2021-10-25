package com.putoet.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {
    private static final String ROUTE = "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$";
    private static final String CHOICE = "(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))";
    private static final String ELEMENT = "WSSEESWWWNW";
    private static final String STRING = ELEMENT + CHOICE;

    @Test
    void get() {
        final Token token = Token.of(STRING);
        assertEquals(STRING, token.get());
    }

    @Test
    void of() {
        Token token = Token.of(STRING);
        assertTrue(token instanceof CompositeElement);

        token = Token.of(ELEMENT);
        assertTrue(token instanceof Element);

        token = Token.of(CHOICE);
        assertTrue(token instanceof Choice);

        token = Token.of(ROUTE);
        assertTrue(token instanceof CompositeElement);
    }

    @Test
    void isChoice() {
        assertTrue(Token.isChoice(CHOICE));
        assertFalse(Token.isChoice(STRING));
    }

    @Test
    void isComposite() {
        assertTrue(Token.isComposite(STRING));
        assertTrue(Token.isComposite(CHOICE));
        assertFalse(Token.isComposite("N"));
    }
}