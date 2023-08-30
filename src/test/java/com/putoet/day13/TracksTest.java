package com.putoet.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TracksTest {

    @Test
    void trackElementAt() {
        assertEquals(TrackElement.HORIZONTAL_LINE, TrackElement.of(Direction.NORTH, '-'));
        assertEquals(TrackElement.VERTICAL_LINE, TrackElement.of(Direction.EAST, '|'));
        assertEquals(TrackElement.INTERSECTION, TrackElement.of(Direction.SOUTH, '+'));
        assertEquals(TrackElement.TOP_LEFT, TrackElement.of(Direction.WEST, '/'));
        assertEquals(TrackElement.TOP_LEFT, TrackElement.of(Direction.NORTH, '/'));
        assertEquals(TrackElement.BOTTOM_RIGHT, TrackElement.of(Direction.EAST, '/'));
        assertEquals(TrackElement.BOTTOM_RIGHT, TrackElement.of(Direction.SOUTH, '/'));
        assertEquals(TrackElement.TOP_RIGHT, TrackElement.of(Direction.EAST, '\\'));
        assertEquals(TrackElement.TOP_RIGHT, TrackElement.of(Direction.NORTH, '\\'));
        assertEquals(TrackElement.BOTTOM_LEFT, TrackElement.of(Direction.WEST, '\\'));
        assertEquals(TrackElement.BOTTOM_LEFT, TrackElement.of(Direction.SOUTH, '\\'));
    }
}