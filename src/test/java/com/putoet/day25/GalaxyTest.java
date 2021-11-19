package com.putoet.day25;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GalaxyTest {
    private static final List<String> GALAXY1 = List.of(
            "0,0,0,0",
            " 3,0,0,0",
            " 0,3,0,0",
            " 0,0,3,0",
            " 0,0,0,3",
            " 0,0,0,6",
            " 9,0,0,0",
            "12,0,0,0"
    );

    private static final List<String> GALAXY2 = List.of(
            "-1,2,2,0",
            "0,0,2,-2",
            "0,0,0,-2",
            "-1,2,0,0",
            "-2,-2,-2,2",
            "3,0,2,-1",
            "-1,3,2,2",
            "-1,0,-1,0",
            "0,2,1,-2",
            "3,0,0,0"
    );

    private static final List<String> GALAXY3 = List.of(
            "1,-1,0,1",
            "2,0,-1,0",
            "3,2,-1,0",
            "0,0,3,1",
            "0,0,-1,-1",
            "2,3,-2,0",
            "-2,2,0,0",
            "2,-2,0,-1",
            "1,-1,0,-1",
            "3,2,0,2"
    );

    private static final List<String> GALAXY4 = List.of(
            "1,-1,-1,-2",
            "-2,-2,0,1",
            "0,2,1,3",
            "-2,3,-2,1",
            "0,2,3,-2",
            "-1,-1,1,-2",
            "0,-2,-1,0",
            "-2,2,3,-1",
            "1,2,2,0",
            "-1,-2,0,-2"
    );

    @Test
    void add() {
        assertEquals(2, new Galaxy().add(pointsOf(GALAXY1)).size());
        assertEquals(4, new Galaxy().add(pointsOf(GALAXY2)).size());
        assertEquals(3, new Galaxy().add(pointsOf(GALAXY3)).size());
        assertEquals(8, new Galaxy().add(pointsOf(GALAXY4)).size());
    }

    private List<Point4D> pointsOf(List<String> lines) {
        return lines.stream().map(Point4D::of).collect(Collectors.toList());
    }
}