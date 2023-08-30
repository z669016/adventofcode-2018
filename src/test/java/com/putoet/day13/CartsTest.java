package com.putoet.day13;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartsTest {

    @Test
    void of() {
        final var tracks = List.of(
                "/->-\\        ",
                "|   |  /----\\",
                "^ /-+--+-\\  |",
                "| | |  | v  |",
                "\\-+-/  \\-+--/",
                "  \\---<---/"
        );

        final var carts = Carts.of(tracks);
        assertEquals(4, carts.size());

        final var iter = carts.iterator();
        var cart = iter.next();
        assertEquals(Point.of(2,0), cart.location());
        assertEquals(Direction.EAST, cart.direction());

        cart = iter.next();
        assertEquals(Point.of(0,2), cart.location());
        assertEquals(Direction.NORTH, cart.direction());

        cart = iter.next();
        assertEquals(Point.of(9,3), cart.location());
        assertEquals(Direction.SOUTH, cart.direction());

        cart = iter.next();
        assertEquals(Point.of(6,5), cart.location());
        assertEquals(Direction.WEST, cart.direction());
        assertFalse(iter.hasNext());
    }

    @Test
    void crash() {
        final var lines = ResourceLines.list("/day13.txt");
        final var tracks = Tracks.of(lines);
        var carts = Carts.of(lines);

        try {
            while (carts != null)
                carts = carts.move(tracks);

            fail();
        } catch (Carts.CartCrashException exc) {
            assertEquals(Point.of(7, 3), exc.cart().location());
        }
    }
}