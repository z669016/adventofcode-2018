package com.putoet.day24;

import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Groups {
    private static final String IMMUNE = "Immune System:";
    private static final String INFECTION = "Infection:";

    private static void initGroupIds() {
        Group.immuneId = 1;
        Group.infectionId = 1;
    }

    public static List<Group> of(List<String> lines) {
        return of(lines, 0);
    }

    public static List<Group> of(List<String> lines, int boost) {
        final List<Group> groups = new ArrayList<>();

        Optional<GroupType> type = Optional.empty();
        for (String line : lines) {
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

    public static List<Pair<Group, Optional<Group>>> targetSelection(List<Group> groups) {
        final List<Group> attackers = targetSelectionOrder(groups);
        final Set<Group> defenders = alive(groups);

        return attackers.stream().map(attacker -> {
                    final Optional<Group> defender = attacker.selectTarget(defenders);
                    defender.ifPresent(defenders::remove);
                    return new Pair<>(attacker, defender);
                })
                .collect(Collectors.toList());
    }

    public static List<Group> targetSelectionOrder(List<Group> groups) {
        groups = groups.stream()
                .filter(Group::isAlive)
                .sorted(Comparator.comparing(Group::effectivePower).thenComparing(Group::initiative).reversed())
                .collect(Collectors.toList());

        return groups;
    }

    public static Set<Group> alive(List<Group> groups) {
        return groups.stream().filter(Group::isAlive).collect(Collectors.toSet());
    }

    public static boolean fight(List<Group> groups) {
        final int beforeUnits = units(groups);

        final List<Pair<Group, Optional<Group>>> targetSSelection = targetSelection(groups);
        targetSSelection.stream()
                .sorted(Comparator.comparing((Pair<Group, Optional<Group>>pair) -> pair.getValue0().initiative()).reversed())
                .forEach(pair -> pair.getValue1().ifPresent(defender -> defender.defend(pair.getValue0())));

        final int afterUnits = units(groups);

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
