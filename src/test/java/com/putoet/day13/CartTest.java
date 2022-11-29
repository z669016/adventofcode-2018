package com.putoet.day13;

import com.putoet.grid.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartTest {
    private Cart one, two, three, four;

    @BeforeEach
    void setup() {
        one = Cart.of(Point.of(1, 1), '^');
        two = Cart.of(Point.of(0, 1), '>');
        three = Cart.of(Point.of(0, 0), 'v');
        four = Cart.of(Point.of(2, 2), '<');
    }

    @Test
    void of() {
        assertEquals(Direction.NORTH, one.direction());
        assertEquals(Direction.EAST, two.direction());
        assertEquals(Direction.SOUTH, three.direction());
        assertEquals(Direction.WEST, four.direction());
    }

    @Test
    void compareTo() {
        final Set<Cart> carts = new TreeSet<>(Set.of(one, two, three, four));
        final Iterator<Cart> iter = carts.iterator();
        assertEquals(three, iter.next());
        assertEquals(two, iter.next());
        assertEquals(one, iter.next());
        assertEquals(four, iter.next());
    }

    @Test
    void directionTurnSupplier() {
        final Supplier<DirectionTurn> directionTurnSupplier = Cart.directionTurnSupplier();

        assertEquals(DirectionTurn.LEFT, directionTurnSupplier.get());
        assertEquals(DirectionTurn.STRAIGHT, directionTurnSupplier.get());
        assertEquals(DirectionTurn.RIGHT, directionTurnSupplier.get());
        assertEquals(DirectionTurn.LEFT, directionTurnSupplier.get());
    }

    @Test
    void moveIntersectionSouth() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                " | ",
                "-+-",
                " | "
        ));

        Cart cart = new Cart("TEST", Direction.SOUTH, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.RIGHT);
        Cart next = cart.move(tracks);
        assertEquals(Point.of(0, 1), next.location());
        assertEquals(Direction.WEST, next.direction());

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.STRAIGHT);
        next = cart.move(tracks);
        assertEquals(Point.of(1, 2), next.location());
        assertEquals(Direction.SOUTH, next.direction());

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.LEFT);
        next = cart.move(tracks);
        assertEquals(Point.of(2, 1), next.location());
        assertEquals(Direction.EAST, next.direction());
    }

    @Test
    void moveIntersectionWest() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                " | ",
                "-+-",
                " | "
        ));

        Cart cart = new Cart("TEST", Direction.WEST, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.RIGHT);
        Cart next = cart.move(tracks);
        assertEquals(Point.of(1, 0), next.location());
        assertEquals(Direction.NORTH, next.direction());

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.STRAIGHT);
        next = cart.move(tracks);
        assertEquals(Point.of(0, 1), next.location());
        assertEquals(Direction.WEST, next.direction());

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.LEFT);
        next = cart.move(tracks);
        assertEquals(Point.of(1, 2), next.location());
        assertEquals(Direction.SOUTH, next.direction());
    }

    @Test
    void moveIntersectionNorth() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                " | ",
                "-+-",
                " | "
        ));

        Cart cart = new Cart("TEST", Direction.NORTH, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.RIGHT);
        Cart next = cart.move(tracks);
        assertEquals(Point.of(2, 1), next.location());
        assertEquals(Direction.EAST, next.direction());

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.STRAIGHT);
        next = cart.move(tracks);
        assertEquals(Point.of(1, 0), next.location());
        assertEquals(Direction.NORTH, next.direction());

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.LEFT);
        next = cart.move(tracks);
        assertEquals(Point.of(0, 1), next.location());
        assertEquals(Direction.WEST, next.direction());
    }

    @Test
    void moveIntersectionEast() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                " | ",
                "-+-",
                " | "
        ));

        Cart cart = new Cart("TEST", Direction.EAST, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.RIGHT);
        Cart next = cart.move(tracks);
        assertEquals(Point.of(1, 2), next.location());
        assertEquals(Direction.SOUTH, next.direction());

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.STRAIGHT);
        next = cart.move(tracks);
        assertEquals(Point.of(2, 1), next.location());
        assertEquals(Direction.EAST, next.direction());

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.LEFT);
        next = cart.move(tracks);
        assertEquals(Point.of(1, 0), next.location());
        assertEquals(Direction.NORTH, next.direction());
    }

    @Test
    void moveHLine() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                "---"
        ));

        Cart west = new Cart("WEST", Direction.WEST, directionTurnSupplier, Point.of(1, 0));
        Cart east = new Cart("EAST", Direction.EAST, directionTurnSupplier, Point.of(1, 0));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        Cart next = east.move(tracks);
        assertEquals(Point.of(2, 0), next.location());
        assertEquals(Direction.EAST, next.direction());

        next = west.move(tracks);
        assertEquals(Point.of(0, 0), next.location());
        assertEquals(Direction.WEST, next.direction());
    }

    @Test
    void moveVLine() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                "|",
                "|",
                "|"
        ));

        Cart north = new Cart("NORTH", Direction.NORTH, directionTurnSupplier, Point.of(0, 1));
        Cart south = new Cart("SOUTH", Direction.SOUTH, directionTurnSupplier, Point.of(0, 1));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        Cart next = north.move(tracks);
        assertEquals(Point.of(0, 0), next.location());
        assertEquals(Direction.NORTH, next.direction());

        next = south.move(tracks);
        assertEquals(Point.of(0, 2), next.location());
        assertEquals(Direction.SOUTH, next.direction());
    }

    @Test
    void moveTopLeft() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                "/-",
                "| "
        ));

        Cart west = new Cart("WEST", Direction.WEST, directionTurnSupplier, Point.of(0, 0));
        Cart north = new Cart("NORTH", Direction.NORTH, directionTurnSupplier, Point.of(0, 0));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        Cart next = west.move(tracks);
        assertEquals(Point.of(0, 1), next.location());
        assertEquals(Direction.SOUTH, next.direction());

        next = north.move(tracks);
        assertEquals(Point.of(1, 0), next.location());
        assertEquals(Direction.EAST, next.direction());
    }

    @Test
    void moveTopRight() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                "-\\",
                " |"
        ));

        Cart east = new Cart("EAST", Direction.EAST, directionTurnSupplier, Point.of(1, 0));
        Cart north = new Cart("NORTH", Direction.NORTH, directionTurnSupplier, Point.of(1, 0));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        Cart next = east.move(tracks);
        assertEquals(Point.of(1, 1), next.location());
        assertEquals(Direction.SOUTH, next.direction());

        next = north.move(tracks);
        assertEquals(Point.of(0, 0), next.location());
        assertEquals(Direction.WEST, next.direction());
    }

    @Test
    void moveBottomLeft() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                "| ",
                "\\-"
        ));

        Cart south = new Cart("SOUTH", Direction.SOUTH, directionTurnSupplier, Point.of(0, 1));
        Cart west = new Cart("WEST", Direction.WEST, directionTurnSupplier, Point.of(0, 1));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        Cart next = south.move(tracks);
        assertEquals(Point.of(1, 1), next.location());
        assertEquals(Direction.EAST, next.direction());

        next = west.move(tracks);
        assertEquals(Point.of(0, 0), next.location());
        assertEquals(Direction.NORTH, next.direction());
    }

    @Test
    void moveBottomRight() {
        final Supplier<DirectionTurn> directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                " |",
                "-/"
        ));

        Cart east = new Cart("EAST", Direction.EAST, directionTurnSupplier, Point.of(1, 1));
        Cart south = new Cart("SOUTH", Direction.SOUTH, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        Cart next = east.move(tracks);
        assertEquals(Point.of(1, 0), next.location());
        assertEquals(Direction.NORTH, next.direction());

        next = south.move(tracks);
        assertEquals(Point.of(0, 1), next.location());
        assertEquals(Direction.WEST, next.direction());
    }
}