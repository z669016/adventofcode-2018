package com.putoet.day23;

import com.putoet.resources.ResourceLines;
import org.javatuples.Pair;

import java.util.*;

public class Day23 {
    public static void main(String[] args) {
        final List<NanoBot> bots = NanoBots.of(ResourceLines.list("/day23.txt"));

        part1(bots);
        part2(bots);
    }

    private static void part1(List<NanoBot> bots) {
        final Optional<NanoBot> strongest = NanoBots.strongest(bots);
        if (strongest.isEmpty())
            throw new IllegalStateException("No strongest bot found in the list");

        final List<NanoBot> inRange = NanoBots.inRange(strongest.get(), bots);
        System.out.println("NanoBots in range of the strongest: " + inRange.size());
    }

    public static void part2(List<NanoBot> bots) {
        final PriorityQueue<Pair<Integer,Integer>> queue = new PriorityQueue<>();
        bots.forEach(bot -> {
            queue.offer(new Pair<>(Math.max(0, bot.coordinate.manhattanDistance() - bot.r), 1));
            queue.offer(new Pair<>(bot.coordinate.manhattanDistance() + bot.r + 1, -1));
        });

        int count = 0;
        int maxCount = 0;
        int result = 0;
        while (!queue.isEmpty()) {
            final Pair<Integer,Integer> pair = queue.poll();
            System.out.println(pair);
            count += pair.getValue1();
            if (count > maxCount) {
                result = pair.getValue0();
                maxCount = count;
            }
        }

        System.out.println(result);
    }
}
