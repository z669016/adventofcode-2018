package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        final var input = ResourceLines.list("/day7.txt");
        Timer.run(() -> part1(input));
        Timer.run(() -> part2(input));
    }

    private static void part1(List<String> input) {
        final var steps = Steps.of(input);
        final var walker = new ConcurrentStepWalker(steps);

        System.out.println("Order is " + walker.walk());
    }

    private static void part2(List<String> input) {
        final var steps = Steps.of(input);
        final var walker = new ConcurrentStepWalker(steps, 5);

        walker.walkMultiple(60);
        System.out.println("Duration is " + walker.duration());
    }
}
