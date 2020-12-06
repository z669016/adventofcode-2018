package com.putoet.day13;

import org.junit.jupiter.api.Test;
import com.putoet.utilities.Direction;

import static org.junit.jupiter.api.Assertions.*;

class TracksTest {

    @Test
    void trackElementAt() {
        assertEquals(Tracks.TrackElement.HLINE, Tracks.TrackElement.of(Direction.NORTH, '-'));
        assertEquals(Tracks.TrackElement.VLINE, Tracks.TrackElement.of(Direction.EAST, '|'));
        assertEquals(Tracks.TrackElement.INTERSECTION, Tracks.TrackElement.of(Direction.SOUTH, '+'));
        assertEquals(Tracks.TrackElement.TOP_LEFT, Tracks.TrackElement.of(Direction.WEST, '/'));
        assertEquals(Tracks.TrackElement.TOP_LEFT, Tracks.TrackElement.of(Direction.NORTH, '/'));
        assertEquals(Tracks.TrackElement.BOTTOM_RIGHT, Tracks.TrackElement.of(Direction.EAST, '/'));
        assertEquals(Tracks.TrackElement.BOTTOM_RIGHT, Tracks.TrackElement.of(Direction.SOUTH, '/'));
        assertEquals(Tracks.TrackElement.TOP_RIGHT, Tracks.TrackElement.of(Direction.EAST, '\\'));
        assertEquals(Tracks.TrackElement.TOP_RIGHT, Tracks.TrackElement.of(Direction.NORTH, '\\'));
        assertEquals(Tracks.TrackElement.BOTTOM_LEFT, Tracks.TrackElement.of(Direction.WEST, '\\'));
        assertEquals(Tracks.TrackElement.BOTTOM_LEFT, Tracks.TrackElement.of(Direction.SOUTH, '\\'));
    }
}