package com.putoet.day23;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;
import org.javatuples.Pair;

import java.util.*;

public class Day23 {
    public static void main(String[] args) {
        final var bots = NanoBots.of(ResourceLines.list("/day23.txt"));

        Timer.run(() -> part1(bots));
        Timer.run(() -> part2(bots));
    }

    static void part1(List<NanoBot> bots) {
        final var strongest = NanoBots.strongest(bots).orElseThrow();
        final var inRange = NanoBots.inRange(strongest, bots);
        System.out.println("Number is NanoBots in range of the strongest is " + inRange.size());
    }

    static void part2(List<NanoBot> bots) {
        final var queue = new PriorityQueue<Pair<Integer, Integer>>();
        bots.forEach(bot -> {
            queue.offer(new Pair<>(Math.max(0, bot.coordinate().manhattanDistance() - bot.r()), 1));
            queue.offer(new Pair<>(bot.coordinate().manhattanDistance() + bot.r() + 1, -1));
        });

        var count = 0;
        var maxCount = 0;
        var result = 0;
        while (!queue.isEmpty()) {
            final var pair = queue.poll();
            count += pair.getValue1();
            if (count > maxCount) {
                result = pair.getValue0();
                maxCount = count;
            }
        }

        System.out.println("The shortest manhattan distance between any of those points and (0,0,0) is " + result);
    }
}
