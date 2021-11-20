package com.putoet.day15;

import com.putoet.resources.ResourceLines;
import org.javatuples.Pair;

import java.util.List;

public class Day15 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day15.txt");
        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        final Game game = GameFactory.of(lines);
        final Pair<UnitType,Integer> winner = game.combat();

        System.out.println("Game was won by " + winner.getValue0() + " with a score of " + winner.getValue1());
    }

    private static void part2(List<String> lines) {
        Pair<UnitType,Integer> winner;
        Game game;
        int elves;

        int elfAttackPower = 3;

        do {
            elfAttackPower++;
            game = GameFactory.of(lines, elfAttackPower);
            elves = game.elves().size();

            System.out.print(elfAttackPower + "\r");
            winner = game.combat();
        } while (winner.getValue0() == UnitType.GOBLIN || (winner.getValue0() == UnitType.ELF && game.elves().size() != elves));

        System.out.println();
        System.out.println("Game was won by " + winner.getValue0() + " with a score of " + winner.getValue1());
    }
}
