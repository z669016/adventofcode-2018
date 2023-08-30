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
        final var carts = new TreeSet<>(Set.of(one, two, three, four));
        final var iter = carts.iterator();
        assertEquals(three, iter.next());
        assertEquals(two, iter.next());
        assertEquals(one, iter.next());
        assertEquals(four, iter.next());
    }

    @Test
    void moveIntersectionSouth() {
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final var tracks = Tracks.of(List.of(
                " | ",
                "-+-",
                " | "
        ));

        final var cart = new Cart("TEST", Direction.SOUTH, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.RIGHT);

        var next = cart.move(tracks);
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
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final var tracks = Tracks.of(List.of(
                " | ",
                "-+-",
                " | "
        ));

        final var cart = new Cart("TEST", Direction.WEST, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.RIGHT);

        var next = cart.move(tracks);
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
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final Tracks tracks = Tracks.of(List.of(
                " | ",
                "-+-",
                " | "
        ));

        final var cart = new Cart("TEST", Direction.NORTH, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.RIGHT);

        var next = cart.move(tracks);
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
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final var tracks = Tracks.of(List.of(
                " | ",
                "-+-",
                " | "
        ));

        final var cart = new Cart("TEST", Direction.EAST, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenReturn(DirectionTurn.RIGHT);

        var next = cart.move(tracks);
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
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final var tracks = Tracks.of(List.of(
                "---"
        ));

        final var west = new Cart("WEST", Direction.WEST, directionTurnSupplier, Point.of(1, 0));
        final var east = new Cart("EAST", Direction.EAST, directionTurnSupplier, Point.of(1, 0));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        var next = east.move(tracks);
        assertEquals(Point.of(2, 0), next.location());
        assertEquals(Direction.EAST, next.direction());

        next = west.move(tracks);
        assertEquals(Point.of(0, 0), next.location());
        assertEquals(Direction.WEST, next.direction());
    }

    @Test
    void moveVLine() {
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final var tracks = Tracks.of(List.of(
                "|",
                "|",
                "|"
        ));

        final var north = new Cart("NORTH", Direction.NORTH, directionTurnSupplier, Point.of(0, 1));
        final var south = new Cart("SOUTH", Direction.SOUTH, directionTurnSupplier, Point.of(0, 1));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        var next = north.move(tracks);
        assertEquals(Point.of(0, 0), next.location());
        assertEquals(Direction.NORTH, next.direction());

        next = south.move(tracks);
        assertEquals(Point.of(0, 2), next.location());
        assertEquals(Direction.SOUTH, next.direction());
    }

    @Test
    void moveTopLeft() {
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final var tracks = Tracks.of(List.of(
                "/-",
                "| "
        ));

        final var west = new Cart("WEST", Direction.WEST, directionTurnSupplier, Point.of(0, 0));
        final var north = new Cart("NORTH", Direction.NORTH, directionTurnSupplier, Point.of(0, 0));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        var next = west.move(tracks);
        assertEquals(Point.of(0, 1), next.location());
        assertEquals(Direction.SOUTH, next.direction());

        next = north.move(tracks);
        assertEquals(Point.of(1, 0), next.location());
        assertEquals(Direction.EAST, next.direction());
    }

    @Test
    void moveTopRight() {
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final var tracks = Tracks.of(List.of(
                "-\\",
                " |"
        ));

        final var east = new Cart("EAST", Direction.EAST, directionTurnSupplier, Point.of(1, 0));
        final var north = new Cart("NORTH", Direction.NORTH, directionTurnSupplier, Point.of(1, 0));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        var next = east.move(tracks);
        assertEquals(Point.of(1, 1), next.location());
        assertEquals(Direction.SOUTH, next.direction());

        next = north.move(tracks);
        assertEquals(Point.of(0, 0), next.location());
        assertEquals(Direction.WEST, next.direction());
    }

    @Test
    void moveBottomLeft() {
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final var tracks = Tracks.of(List.of(
                "| ",
                "\\-"
        ));

        final var south = new Cart("SOUTH", Direction.SOUTH, directionTurnSupplier, Point.of(0, 1));
        final var west = new Cart("WEST", Direction.WEST, directionTurnSupplier, Point.of(0, 1));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        var next = south.move(tracks);
        assertEquals(Point.of(1, 1), next.location());
        assertEquals(Direction.EAST, next.direction());

        next = west.move(tracks);
        assertEquals(Point.of(0, 0), next.location());
        assertEquals(Direction.NORTH, next.direction());
    }

    @Test
    void moveBottomRight() {
        @SuppressWarnings("unchecked")
        final var directionTurnSupplier = (Supplier<DirectionTurn>) mock(Supplier.class);
        final var tracks = Tracks.of(List.of(
                " |",
                "-/"
        ));

        final var east = new Cart("EAST", Direction.EAST, directionTurnSupplier, Point.of(1, 1));
        final var south = new Cart("SOUTH", Direction.SOUTH, directionTurnSupplier, Point.of(1, 1));

        when(directionTurnSupplier.get()).thenThrow(new AssertionError());

        var next = east.move(tracks);
        assertEquals(Point.of(1, 0), next.location());
        assertEquals(Direction.NORTH, next.direction());

        next = south.move(tracks);
        assertEquals(Point.of(0, 1), next.location());
        assertEquals(Direction.WEST, next.direction());
    }
}