package com.putoet.day9;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stock implements Iterator<Integer> {
    private final int last;
    private int next = 1;

    public Stock(int last) {
        this.last = last;
    }

    @Override
    public boolean hasNext() {
        return next <= last;
    }

    @Override
    public Integer next() {
        if (hasNext())
            return next++;

        throw new NoSuchElementException();
    }
}
