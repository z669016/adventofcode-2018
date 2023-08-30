package com.putoet.day12;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day12 {
    public static void main(String[] args) {
        Timer.run(Day12::part1);
        Timer.run(Day12::part2);
    }

    private static void part1() {
        var pots = Pots.of(ResourceLines.list("/day12.txt"));

        for (int i = 0; i < 20; i++)
            pots = pots.next();

        System.out.println("After 20 generations the sum of the numbers of all pots which contain a plant is " + pots.potSum());
    }

    private static void part2() {
        var pots = Pots.of(ResourceLines.list("/day12.txt"));

        for (int i = 0; i < 1_000; i++)
            pots = pots.next();

        final long many = 50_000_000_000L;
        final long offset = many - 1_000 + pots.offset();

        System.out.println("After " + many + " generations the sum of the numbers of all pots which contain a plant is " + pots.potSum(offset));
    }
}
