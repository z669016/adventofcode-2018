package com.putoet.day14;

import java.util.ArrayList;

public class CircularList<T> extends ArrayList<T> {
    public CircularList(int initialSize) {
        super(initialSize);
    }

    @Override
    public T get(int index) {
        if (size() == 0 || index < 0)
            throw new IndexOutOfBoundsException("Invalid index " + index + " for CircularList (size " + size() + ")");

        if (index >= size())
            return super.get(index % size());

        return super.get(index);
    }
}
