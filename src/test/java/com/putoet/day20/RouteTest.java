package com.putoet.day20;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteTest {

    @Test
    void minimalLength() {
        final Route route = new Route("WNE");
        assertEquals(List.of(
                Point.of(0, 0),
                Point.of(-1, 0),
                Point.of(-1, 1),
                Point.of(0, 1)), route.asPoints());
    }
}