package com.putoet.day7;

import com.putoet.resources.ResourceLines;

public class Day7 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        final Steps steps = Steps.of(ResourceLines.list("/day7.txt"));
        final ConcurrentStepWalker walker = new ConcurrentStepWalker(steps);

        System.out.println("Order is " + walker.walk());
    }

    private static void part2() {
        final Steps steps = Steps.of(ResourceLines.list("/day7.txt"));
        final ConcurrentStepWalker walker = new ConcurrentStepWalker(steps, 5);

        System.out.println("Order is " + walker.walkMultiple(60));
        System.out.println("Duration is " + walker.duration());
    }
}
