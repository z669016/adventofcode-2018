package com.putoet.day15;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day15 {
    public static void main(String[] args) {
        final var lines = ResourceLines.list("/day15.txt");
        Timer.run(() -> part1(lines));
        Timer.run(() -> part2(lines));
    }

    private static void part1(List<String> lines) {
        final var game = GameFactory.of(lines);
        final var outcome = game.combat();

        System.out.println("Game was won by " + outcome.unit() + " with a score of " + outcome.score());
    }

    private static void part2(List<String> lines) {
        Outcome outcome;
        Game game;
        int elves;

        int elfAttackPower = 3;

        do {
            elfAttackPower++;
            game = GameFactory.of(lines, elfAttackPower);
            elves = game.elves().size();

            System.out.print(elfAttackPower + "\r");
            outcome = game.combat();
        } while (outcome.unit() == UnitType.GOBLIN || (outcome.unit() == UnitType.ELF && game.elves().size() != elves));

        System.out.println();
        System.out.println("Game was won by " + outcome.unit() + " with a score of " + outcome.score());
    }
}
