package com.putoet.day18;

import com.putoet.resources.ResourceLines;
import utilities.Grid;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day18 {
    public static void main(String[] args) {
        part(10);
        part(1_000_000_000);
    }

    private static void part(long minutes) {
        final Set<Integer> set = new HashSet<>();
        Grid next = GridFactory.of(ResourceLines.list("/day18.txt"));

        set.add(Objects.hash(next));
        for (long count = 0; count < minutes; count++) {
            System.out.print(count + "\r");
            next = GridFactory.next(next);
            final int hash = Objects.hash(next);
            if (!set.add(hash)) {
                break;
            }
        }

        // if we encountered a double, then redo the attempt with adjusted minutes
        if (set.size() < minutes) {
            part(minutes % (set.size() - 1));
        } else {
            System.out.println("Total resource value after " + minutes + " minutes is " + GridFactory.totalResourceValue(next));
        }
    }
}
