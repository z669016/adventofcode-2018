package com.putoet.day14;

import java.util.ArrayList;

class CircularList<T> extends ArrayList<T> {
    public CircularList(int initialSize) {
        super(initialSize);
    }

    @Override
    public T get(int index) {
        if (size() == 0 || index < 0)
            throw new IndexOutOfBoundsException("Invalid index " + index + " for CircularList (size " + size() + ")");

        index = (index >= size() ? index % size() : index);
        return super.get(index);
    }
}
