package com.putoet.day15;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameFactoryTest {
    private static final List<String> GRID1 = List.of(
            "#######",
            "#.G...#   G(200)",
            "#...EG#   E(200), G(200)",
            "#.#.#G#   G(200)",
            "#..G#E#   G(200), E(200)",
            "#.....#",
            "#######"
    );

    @Test
    void of() {
        final int length = GRID1.get(0).length();
        assertEquals(String.join("\n", GRID1), GameFactory.of(GRID1.stream().map(s->s.substring(0, length)).collect(Collectors.toList())).toString());
    }
}