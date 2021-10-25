package com.putoet.day20;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MapReaderTest {

    @Test
    void of() {
    }

    @Test
    void testOf() {
    }

    @Test
    void updateRoomsForToken() {
    }

    @Test
    void updateRoomsForChoice() {
    }

    @Test
    void updateRoomsForCompositeElement() {
    }

    @Test
    void updateRoomsForElement() {
        final String token = "WNES";
        final Element element = (Element) Token.of(token);

        Stack<Point> current = new Stack<>();
        current.push(Point.ORIGIN);

        final Set<Point> rooms = new HashSet<>(current);
        current = MapReader.updateRoomsForElement(element, current, rooms);

        assertEquals(1, current.size());
        assertEquals(4, rooms.size());

        assertEquals(Point.ORIGIN, current.pop());
        assertEquals(Set.of(Point.of(0, 0), Point.of(-2, -2), Point.of(0, -2), Point.of(-2, 0)), rooms);

        System.out.println(MapReader.asGrid(rooms));
    }

    @Test
    void asGrid() {
    }
}