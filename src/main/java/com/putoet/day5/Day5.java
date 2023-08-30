package com.putoet.day5;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day5 {
    public static void main(String[] args) {
        final var polymers = ResourceLines.list("/day5.txt");
        final var polymer = Timer.run(() -> part1(polymers));
        Timer.run(() -> part2(polymer));
    }

    private static String part1(List<String> polymers) {
        final var polymer = polymers.get(0);
        System.out.println("Remaining # units for input after reaction is " + Polymers.react(polymer).length());
        return polymer;
    }

    private static void part2(String polymer) {
        final var units = Polymers.units(polymer);
        final var shortest = units.chars().map(c -> Polymers.reactWithout(polymer, (char) c).length()).min().orElseThrow();
        System.out.println("The shortest possible polymer has length " + shortest);
    }
}
