package com.putoet.day20;

import com.putoet.grid.Grid;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

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
        final Grid grid = GridFactory.of(regex);
        final RoomFinder roomFinder = new RoomFinder(grid);
        return roomFinder;
    }

    @Test
    void furthestRoom() {
        assertEquals(10, getRoomFinder(GridFactoryTest.REGEX1).furthestRoom().getAsInt());
        assertEquals(18, getRoomFinder(GridFactoryTest.REGEX2).furthestRoom().getAsInt());
        assertEquals(23, getRoomFinder(GridFactoryTest.REGEX3).furthestRoom().getAsInt());
        assertEquals(31, getRoomFinder(GridFactoryTest.REGEX4).furthestRoom().getAsInt());
    }

    @Test
    void doorsTo() {
        final String[] regexes = { GridFactoryTest.REGEX1,
                GridFactoryTest.REGEX2,
                GridFactoryTest.REGEX3,
                GridFactoryTest.REGEX4
        };
        for (int idx = 0; idx < regexes.length; idx++) {
            final RoomFinder finder = getRoomFinder(regexes[idx]);
            System.out.print("Number of doors to rooms on grid " + (idx + 1) + ":");
            System.out.println(finder.doorsTo(finder.rooms()).stream().sorted().collect(Collectors.toList()));
        }
    }
}