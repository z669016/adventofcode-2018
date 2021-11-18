package com.putoet.day24;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Group {
    public static final String IMMUNE_TO = "immune to ";
    public static final String WEAK_TO = "weak to ";
    // 2667 units each with 9631 hit points (immune to cold; weak to radiation) with an attack that does 33 radiation damage at initiative 3
    private static final Pattern GROUP = Pattern.compile("(\\d+) units each with (\\d+) hit points (\\([a-z ;,]+\\) )?with an attack that does (\\d+) ([a-z]+) damage at initiative (\\d+)");
    public static int immuneId = 1;
    public static int infectionId = 1;

    private final int id;
    private final GroupType type;
    private final int hitPoints;
    private final Set<String> weaknesses;
    private final Set<String> immunities;
    private final String attackType;
    private final int attackDamage;
    private final int initiative;
    private int units;

    private Group(int id, GroupType type, int units, int hitPoints, Set<String> immunities, Set<String> weaknesses,
                  int attackDamage, String attackType, int initiative) {
        this.id = id;
        this.type = type;
        this.units = units;
        this.hitPoints = hitPoints;
        this.immunities = immunities;
        this.weaknesses = weaknesses;
        this.attackType = attackType;
        this.attackDamage = attackDamage;
        this.initiative = initiative;
    }

    public static Group of(GroupType type, String line) {
        return of(type, line, 0);
    }

    public static Group of(GroupType type, String line, int boost) {
        assert type != null;
        assert line != null;
        assert boost >= 0;

        final Matcher matcher = GROUP.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid group definition: " + line);

        final int units = Integer.parseInt(matcher.group(1));
        final int hitPoints = Integer.parseInt(matcher.group(2));
        final String immunitiesAndWeaknesses = matcher.group(3);
        final Set<String> immunities = immunitiesOf(immunitiesAndWeaknesses);
        final Set<String> weaknesses = weaknessesOf(immunitiesAndWeaknesses);
        final int attackDamage = Integer.parseInt(matcher.group(4)) + (type == GroupType.IMMUNE_SYSTEM ? boost : 0);
        final String attackType = matcher.group(5);
        final int initiative = Integer.parseInt(matcher.group(6));

        return new Group(type == GroupType.IMMUNE_SYSTEM ? immuneId++ : infectionId++,
                type, units, hitPoints, immunities, weaknesses, attackDamage, attackType, initiative);
    }

    private static Set<String> immunitiesOf(String immunitiesAndWeaknesses) {
        return immunitiesOrWeaknessesOf(IMMUNE_TO, immunitiesAndWeaknesses);
    }

    private static Set<String> weaknessesOf(String immunitiesAndWeaknesses) {
        return immunitiesOrWeaknessesOf(WEAK_TO, immunitiesAndWeaknesses);
    }

    private static Set<String> immunitiesOrWeaknessesOf(String label, String immunitiesAndWeaknesses) {
        if (immunitiesAndWeaknesses == null || immunitiesAndWeaknesses.isBlank())
            return Set.of();

        immunitiesAndWeaknesses = immunitiesAndWeaknesses.substring(1, immunitiesAndWeaknesses.length() - 2);
        final String[] split = immunitiesAndWeaknesses.split("; ");
        for (String line : split) {
            line = line.strip();
            if (line.startsWith(label)) {
                return Arrays.stream(line.substring(label.length()).split(", ")).collect(Collectors.toSet());
            }
        }

        return Set.of();
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", type=" + type +
                ", hitPoints=" + hitPoints +
                ", weaknesses=" + weaknesses +
                ", immunities=" + immunities +
                ", attackType='" + attackType + '\'' +
                ", attackDamage=" + attackDamage +
                ", initiative=" + initiative +
                ", units=" + units +
                '}';
    }

    public int id() {
        return id;
    }

    public GroupType type() {
        return type;
    }

    public int units() {
        return units;
    }

    public int hitPoints() {
        return hitPoints;
    }

    public Set<String> immunities() {
        return immunities;
    }

    public Set<String> weaknesses() {
        return weaknesses;
    }

    public int attackDamage() {
        return attackDamage;
    }

    public String attackType() {
        return attackType;
    }

    public int initiative() {
        return initiative;
    }

    public int effectivePower() {
        return units() * attackDamage();
    }

    public boolean isAlive() {
        return units > 0;
    }

    public int possibleDamage(Group attacker) {
        assert this.type != attacker.type;

        if (immunities.contains(attacker.attackType()))
            return 0;

        return attacker.effectivePower() * (weaknesses.contains(attacker.attackType()) ? 2 : 1);
    }

    public Group defend(Group attacker) {
        assert type != attacker.type;
        assert units > 0;

        final int oldUnits = units;

        final int possibleDamage = possibleDamage(attacker);
        units = Math.max(0, (int) Math.ceil((1.0 * units * hitPoints - possibleDamage) / hitPoints));

//        Infection group 2 attacks defending group 2, killing 84 units
//        System.out.printf("%s group %d attacks defending group %d, killing %d units, remaining %d%n"
//                , attacker.type
//                , attacker.id
//                , id
//                , oldUnits - units
//                , units
//        );

        return this;
    }

    public Optional<Group> selectTarget(Set<Group> defenders) {
        if (defenders.isEmpty())
            return Optional.empty();

        return defenders.stream()
                .filter(group -> group.type != type)
                .filter(defender -> defender.possibleDamage(this) > 0)
                .max(Comparator.comparing((Group defender) -> defender.possibleDamage(this)).thenComparing(Group::effectivePower));
    }
}
