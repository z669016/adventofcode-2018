package com.putoet.day20;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day20Test {
    private Rooms rooms;
    private OptionalInt max;

    @Test
    void part1a() {
        rooms = Rooms.of(Routes.of("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$"));
        Rooms.print(rooms);

        max = Day20.part1(rooms);
        assertTrue(max.isPresent());
        assertEquals(18, max.getAsInt());
    }

    @Test
    void part1b() {
        rooms = Rooms.of(Routes.of("^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$"));
        Rooms.print(rooms);

        max = Day20.part1(rooms);
        assertTrue(max.isPresent());
        assertEquals(23, max.getAsInt());
    }

    @Test
    void part1c() {
        rooms = Rooms.of(Routes.of("^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$"));
        Rooms.print(rooms);

        max = Day20.part1(rooms);
        assertTrue(max.isPresent());
        assertEquals(31, max.getAsInt());
    }
}