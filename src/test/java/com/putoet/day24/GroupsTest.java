package com.putoet.day24;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupsTest {
    private final List<String> lines = ResourceLines.list("/day24.txt");

    @Test
    void of() {
        final var groups = Groups.of(lines);
        assertEquals(20, groups.size());
        assertEquals(10, groups.stream().filter(g -> g.type() == GroupType.IMMUNE_SYSTEM).count());
        assertEquals(10, groups.stream().filter(g -> g.type() == GroupType.INFECTION).count());
    }

   @Test
    void targetSelectionOrder() {
        final var groups = Groups.of(lines);
        final var targetSelectionOrder = Groups.targetSelectionOrder(groups);

        targetSelectionOrder.forEach(group -> System.out.println(group.effectivePower() + " " + group.initiative() + " " + group));
    }

    @Test
    void fight() {
        final var groups = Groups.of(ResourceLines.list("/day24-1.txt"));

        //noinspection StatementWithEmptyBody
        while (Groups.fight(groups));

        assertEquals(0, Groups.immuneSystemUnits(groups));
        assertEquals(5216, Groups.infectionUnits(groups));
    }
}