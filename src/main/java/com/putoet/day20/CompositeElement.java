package com.putoet.day20;

import java.util.Iterator;

public class CompositeElement extends Token implements Iterable<Token>{
    protected CompositeElement(String token) {
        super(token);
    }

    @Override
    public Iterator<Token> iterator() {
        return new CompositeElementIterator(this);
    }
}
