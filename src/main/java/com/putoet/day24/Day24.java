package com.putoet.day24;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day24 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        final List<Group> groups = Groups.of(ResourceLines.list("/day24.txt"));
        while (Groups.fight(groups) && (Groups.immuneSystemUnits(groups) != 0 && Groups.infectionUnits(groups) != 0)) {
            // noop
        }

        System.out.println("Immune System units: " + Groups.immuneSystemUnits(groups));
        System.out.println("Infection units: " + Groups.infectionUnits(groups));
    }

    private static void part2() {
        List<Group> groups = List.of();
        int immuneSystemUnits = 0;
        int boost = 0;

        while (immuneSystemUnits == 0) {
            boost += 1;
            System.out.print(boost);
            System.out.print('\r');

            groups = Groups.of(ResourceLines.list("/day24.txt"), boost);
            while (Groups.fight(groups) && (Groups.immuneSystemUnits(groups) != 0 && Groups.infectionUnits(groups) != 0)) {
                // noop
            }

            if (Groups.immuneSystemUnits(groups) != 0 && Groups.infectionUnits(groups) != 0) {
                System.out.println("TIE, with boost " + boost);
                continue;
            }

            immuneSystemUnits = Groups.immuneSystemUnits(groups);
        }

        System.out.println("Boost is: " + boost);
        System.out.println("Immune System units: " + Groups.immuneSystemUnits(groups));
        System.out.println("Infection units: " + Groups.infectionUnits(groups));
    }
}
