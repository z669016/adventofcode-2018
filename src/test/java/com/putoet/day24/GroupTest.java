package com.putoet.day24;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {
    private static final String LINE1 = "2667 units each with 9631 hit points (immune to cold; weak to radiation) with an attack that does 33 radiation damage at initiative 3";
    private static final String LINE2 = "6889 units each with 7044 hit points (immune to cold, slashing) with an attack that does 8 cold damage at initiative 11";
    private static final String LINE3 ="303 units each with 10428 hit points with an attack that does 328 radiation damage at initiative 13";
    private static final String LINE4 ="18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10";
    private static final String LINE5 ="18 units each with 729 hit points (weak to fire; immune to radiation, slashing) with an attack that does 8 radiation damage at initiative 10";
    private static final String LINE6 ="3 units each with 10 hit points with an attack that does 25 radiation damage at initiative 10";
    private static final String LINE7 ="10 units each with 10 hit points with an attack that does 8 radiation damage at initiative 10";

    @Test
    void of() {
        var group = Group.of(GroupType.INFECTION,LINE1);
        assertEquals(GroupType.INFECTION, group.type());
        assertEquals(2667, group.units());
        assertEquals(9631, group.hitPoints());

        assertEquals(1, group.immunities().size());
        assertTrue(group.immunities().contains("cold"));

        assertEquals(1, group.weaknesses().size());
        assertTrue(group.weaknesses().contains("radiation"));

        assertEquals(33, group.attackDamage());
        assertEquals("radiation", group.attackType());
        assertEquals(3, group.initiative());

        group = Group.of(GroupType.INFECTION, LINE2);
        assertTrue(group.weaknesses().isEmpty());
        assertEquals(Set.of("cold", "slashing"), group.immunities());

        group = Group.of(GroupType.INFECTION, LINE3);
        assertTrue(group.immunities().isEmpty());
        assertTrue(group.weaknesses().isEmpty());

        group = Group.of(GroupType.IMMUNE_SYSTEM, LINE4);
        assertEquals(GroupType.IMMUNE_SYSTEM, group.type());
        assertEquals(144, group.effectivePower());
    }

    @Test
    void possibleDamage() {
        final var radiationAttack = Group.of(GroupType.INFECTION, LINE4);
        final var radiationWeak = Group.of(GroupType.IMMUNE_SYSTEM, LINE1);
        final var noneWeak = Group.of(GroupType.IMMUNE_SYSTEM, LINE3);
        final var immuneRadiation = Group.of(GroupType.IMMUNE_SYSTEM, LINE5);

        assertEquals(144, noneWeak.possibleDamage(radiationAttack));
        assertEquals(288, radiationWeak.possibleDamage(radiationAttack));
        assertEquals(0, immuneRadiation.possibleDamage(radiationAttack));

        assertThrows(AssertionError.class, () -> radiationAttack.possibleDamage(radiationAttack));
    }

    @Test
    void attack() {
        final var attacker = Group.of(GroupType.INFECTION, LINE6);
        final var defender = Group.of(GroupType.IMMUNE_SYSTEM, LINE7);

        defender.defend(attacker);
        assertEquals(3, defender.units());
    }
}