package com.putoet.day20;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomsTest {

    @Test
    void addRoomsSimple() {
        final Route route = new Route("WNE");
        final Rooms rooms = Rooms.of(List.of(route));

        Rooms.print(rooms);

        assertEquals(4, rooms.size());
        assertEquals(2, rooms.doors());
    }


    @Test
    void addRoomsComplicated() {
        final Rooms rooms = Rooms.of(Routes.of("^ENWWW(NEEE|SSE(EE|N))$"));

        Rooms.print(rooms);

        assertEquals(4, rooms.size());
        assertEquals(2, rooms.doors());
    }
}