package com.putoet.day10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Point;

import static org.junit.jupiter.api.Assertions.*;

class MovingPointTest {
    private MovingPoint movingPoint;

    @BeforeEach
    void setup() {
        movingPoint = MovingPoint.of("position=< 9,  1> velocity=< 0,  2>");
    }

    @Test
    void move() {
        movingPoint.move();
        assertEquals(Point.of(9, 3), movingPoint.position());
    }

    @Test
    void of() {
        assertEquals(Point.of(9, 1), movingPoint.position());
    }
}