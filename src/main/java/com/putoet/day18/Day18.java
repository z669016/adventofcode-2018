package com.putoet.day18;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;

import java.util.*;

public class Day18 {
    public static void main(String[] args) {
        part(10);
        part(1_000_000_000);
    }

    private static void part(long minutes) {
        final List<Integer> history = new ArrayList<>();
        Grid next = GridFactory.of(ResourceLines.list("/day18.txt"));

        history.add(next.hashCode());
        long count = minutes;
        int firstDuplicate = 0;
        while (count-- > 0) {
            System.out.print(count + "\r");
            next = GridFactory.next(next);

            final int hash = next.hashCode();
            firstDuplicate = history.indexOf(hash);
            if (firstDuplicate != -1) {
                break;
            }
            history.add(hash);
        }

        // if we encountered a double, then redo the attempt with adjusted minutes
        // doubles occur with 614 elements in the set, the first double is at offset 558
        //
        // so the first 558 elements are unique (0 ... 557), then the series from 558...613 keep repeating
        // the element at position 558 + 1_000_000_000 is (1_000_000_000 - 558) % (614 - 558)
        if (history.size() < minutes) {
            part(firstDuplicate + ((minutes - firstDuplicate) % (history.size() - firstDuplicate)));
        } else {
            System.out.println("Total resource value after " + minutes + " minutes is " + GridFactory.totalResourceValue(next));
        }
    }
}
