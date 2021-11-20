package com.putoet.day15;

import com.putoet.resources.ResourceLines;
import org.javatuples.Pair;

import java.util.List;

public class Day15 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day15.txt");
        part1(lines);
    }

    private static void part1(List<String> lines) {
        final Game game = GameFactory.of(lines);
        final Pair<UnitType,Integer> winner = game.combat();

        System.out.println("Game was won by " + winner.getValue0() + " with a score of " + winner.getValue1());

        // 238476 is not the right answer
        // 236138 is not the right answer
    }
}
