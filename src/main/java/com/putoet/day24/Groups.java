package com.putoet.day24;

import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class Groups {
    private static final String IMMUNE = "Immune System:";
    private static final String INFECTION = "Infection:";

    public static List<Group> of(@NotNull List<String> lines) {
        return of(lines, 0);
    }

    public static List<Group> of(@NotNull List<String> lines, int boost) {
        final var groups = new ArrayList<Group>();

        Optional<GroupType> type = Optional.empty();
        for (var line : lines) {
            if (line.strip().isBlank())
                continue;

            if (line.equals(IMMUNE) || line.equals(INFECTION)) {
                type = Optional.of(line.equals(IMMUNE) ? GroupType.IMMUNE_SYSTEM : GroupType.INFECTION);
                continue;
            }

            type.ifPresent(groupType -> groups.add(Group.of(groupType, line, boost)));
        }

        return groups;
    }

    public static List<Pair<Group, Optional<Group>>> targetSelection(@NotNull List<Group> groups) {
        final var attackers = targetSelectionOrder(groups);
        final var defenders = alive(groups);

        return attackers.stream().map(attacker -> {
                    final var defender = attacker.selectTarget(defenders);
                    defender.ifPresent(defenders::remove);
                    return Pair.with(attacker, defender);
                })
                .toList();
    }

    public static List<Group> targetSelectionOrder(@NotNull List<Group> groups) {
        groups = groups.stream()
                .filter(Group::isAlive)
                .sorted(Comparator.comparing(Group::effectivePower).thenComparing(Group::initiative).reversed())
                .toList();

        return groups;
    }

    public static Set<Group> alive(@NotNull List<Group> groups) {
        return groups.stream().filter(Group::isAlive).collect(Collectors.toSet());
    }

    public static boolean fight(@NotNull List<Group> groups) {
        final var beforeUnits = units(groups);

        final var targetSSelection = targetSelection(groups);
        targetSSelection.stream()
                .sorted(Comparator.comparing((Pair<Group, Optional<Group>>pair) -> pair.getValue0().initiative()).reversed())
                .forEach(pair -> pair.getValue1().ifPresent(defender -> defender.defend(pair.getValue0())));

        final var afterUnits = units(groups);
        return beforeUnits > afterUnits;
    }

    public static int immuneSystemUnits(List<Group> groups) {
        return units(groups, GroupType.IMMUNE_SYSTEM);
    }

    public static int infectionUnits(List<Group> groups) {
        return units(groups, GroupType.INFECTION);
    }

    private static int units(List<Group> groups, GroupType type) {
        return groups.stream()
                .filter(group -> group.type() == type)
                .mapToInt(Group::units)
                .sum();
    }

    private static int units(List<Group> groups) {
        return groups.stream()
                .mapToInt(Group::units)
                .sum();
    }
}
