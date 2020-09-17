package com.putoet.day5;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.OptionalInt;

public class Day5 {
    public static void main(String[] args) {
        final List<String> polymers = ResourceLines.list("/day5.txt");
        final String polymer = polymers.get(0);
        System.out.println("Remaining # units for input after reaction is " + Polymers.react(polymer).length());

        final String units = Polymers.units(polymer);
        final OptionalInt shortest =
                units.chars().map(c -> Polymers.reactWithout(polymer, (char) c).length()).min();
        if (shortest.isPresent())
            System.out.println("The shortest possible polymer has length " + shortest.getAsInt());
    }
}
