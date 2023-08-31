package com.putoet.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomFinderTest {
    @Test
    void rooms() {
        assertEquals(15, getRoomFinder(GridFactoryTest.REGEX1).rooms().size());
        assertEquals(24, getRoomFinder(GridFactoryTest.REGEX2).rooms().size());
        assertEquals(35, getRoomFinder(GridFactoryTest.REGEX3).rooms().size());
        assertEquals(48, getRoomFinder(GridFactoryTest.REGEX4).rooms().size());
    }

    private RoomFinder getRoomFinder(String regex) {
        return new RoomFinder(GridFactory.of(regex));
    }

    @Test
    void furthestRoom() {
        assertEquals(10, getRoomFinder(GridFactoryTest.REGEX1).furthestRoom().orElseThrow());
        assertEquals(18, getRoomFinder(GridFactoryTest.REGEX2).furthestRoom().orElseThrow());
        assertEquals(23, getRoomFinder(GridFactoryTest.REGEX3).furthestRoom().orElseThrow());
        assertEquals(31, getRoomFinder(GridFactoryTest.REGEX4).furthestRoom().orElseThrow());
    }

    @Test
    void doorsTo() {
        final var regexes = new String[] {GridFactoryTest.REGEX1,
                GridFactoryTest.REGEX2,
                GridFactoryTest.REGEX3,
                GridFactoryTest.REGEX4
        };
        for (var idx = 0; idx < regexes.length; idx++) {
            final var finder = getRoomFinder(regexes[idx]);
            System.out.print("Number of doors to rooms on grid " + (idx + 1) + ":");
            System.out.println(finder.doorsTo(finder.rooms()).stream().sorted().toList());
        }
    }
}