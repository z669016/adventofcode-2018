package com.putoet.day12;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PotsTest {
    private Pots pots;

    @BeforeEach
    void setup() {
        pots = Pots.of(ResourceLines.list("/day12.txt"));
    }

    @Test
    void size() {
        final var pots = new Pots("#..#.#..##......###...###", Set.of());
        assertTrue(pots.toString().startsWith("....."));
        assertTrue(pots.toString().endsWith("....."));
    }

    @Test
    void of() {
        assertEquals(14, pots.spread().size());
        assertEquals(5, pots.zero());
    }

    @Test
    void next() {
        final var nextList = List.of(
                "...#..#.#..##......###...###..",
                "...#...#....#.....#..#..#..#..",
                "...##..##...##....#..#..#..##..",
                "..#.#...#..#.#....#..#..#...#..",
                "...#.#..#...#.#...#..#..##..##..",
                "....#...##...#.#..#..#...#...#..",
                "....##.#.#....#...#..##..##..##..",
                "...#..###.#...##..#...#...#...#..",
                "...#....##.#.#.#..##..##..##..##..",
                "...##..#..#####....#...#...#...#..",
                "..#.#..#...#.##....##..##..##..##..",
                "...#...##...#.#...#.#...#...#...#..",
                "...##.#.#....#.#...#.#..##..##..##..",
                "..#..###.#....#.#...#....#...#...#..",
                "..#....##.#....#.#..##...##..##..##..",
                "..##..#..#.#....#....#..#.#...#...#..",
                ".#.#..#...#.#...##...#...#.#..##..##..",
                "..#...##...#.#.#.#...##...#....#...#..",
                "..##.#.#....#####.#.#.#...##...##..##..",
                ".#..###.#..#.#.#######.#.#.#..#.#...#..",
                ".#....##....#####...#######....#.#..##.."
        );

        for (int i = 0; i < 20; i++) {
            assertTrue(pots.toString().contains(nextList.get(i)));
            pots = pots.next();
        }

        assertEquals(pots.zero() - 2, pots.toString().indexOf('#') + pots.offset());
        assertEquals(325, pots.potSum());
    }

    @Test
    void bignumber() {
        final var count = 1_000L;
        var first = 0;
        final var start = System.currentTimeMillis();
        for (var i = 0L; i < count; i++) {
            final var next = pots.next();

            if (pots.toString().equals(next.toString()) && first < 10) {
                System.out.println("Unchanged at generation " + i);
                first++;
                System.out.println(pots);
                System.out.println("zero from " + pots.zero() + " to " + next.zero());
                System.out.println("offset from " + pots.offset() + " to " + next.offset());
            }

            pots = next;
        }
        final var end = System.currentTimeMillis();

        System.out.println("The run for " + count + " generations took " + (end - start) + " ms");
        System.out.printf("zero: %d, offset: %d, pots: %s%n", pots.zero(), pots.offset(), pots.toString());
    }
}