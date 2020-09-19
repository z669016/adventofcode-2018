package com.putoet.day9;

import java.util.ArrayList;
import java.util.List;

public class CircleArrayList implements Circle {
    private final List<Integer> list = new ArrayList<>();

    public CircleArrayList() {
        list.add(0);
    }

    private int current = 0;

    @Override
    public int place(int marble) {
        int score = 0;
        if (marble % 23 == 0) {
            score += marble;
            counterclockWise();
            score += list.remove(current);
        } else {
            clockWise();
            if (current == 0) current = 1;
            list.add(current, marble);
        }

        return score;
    }

    private void clockWise() {
        int steps = 2;
        while (steps > 0) {
            current++; steps--;
            if ((current) > list.size())
                current = 0;
        }
    }

    private void counterclockWise() {
        int steps = 7;
        while (steps > 0) {
            current--; steps--;
            if (current < 0)
                current = list.size() - 1;
        }
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < list.size(); idx++)
            sb.append(idx == current ? "(" + list.get(idx) + ")" : list.get(idx)).append(" ");

        return sb.toString();
    }
}
