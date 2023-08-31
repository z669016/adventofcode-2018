package com.putoet.day24;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day24 {
    public static void main(String[] args) {
        final var lines = ResourceLines.list("/day24.txt");

        Timer.run(() -> part1(lines));
        Timer.run(() -> part2(lines));
    }

    static void part1(List<String> lines) {
        final var groups = Groups.of(lines);

        //noinspection StatementWithEmptyBody
        while (Groups.fight(groups) && (Groups.immuneSystemUnits(groups) != 0 && Groups.infectionUnits(groups) != 0)) {
            // noop
        }

        System.out.println("Infection units: " + Groups.infectionUnits(groups));
    }

    static void part2(List<String> lines) {
        List<Group> groups = List.of();
        int immuneSystemUnits = 0;
        int boost = 0;

        while (immuneSystemUnits == 0) {
            boost += 1;
            groups = Groups.of(lines, boost);

            //noinspection StatementWithEmptyBody
            while (Groups.fight(groups) && (Groups.immuneSystemUnits(groups) != 0 && Groups.infectionUnits(groups) != 0)) {
                // noop
            }

            if (Groups.immuneSystemUnits(groups) != 0 && Groups.infectionUnits(groups) != 0) {
                continue;
            }

            immuneSystemUnits = Groups.immuneSystemUnits(groups);
        }

        System.out.println("Immune System units: " + Groups.immuneSystemUnits(groups));
    }
}
