package com.putoet.day6;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Point;
import utilities.PointFactory;
import utilities.Size;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private List<Point> points;
    private Grid grid;

    @BeforeEach
    void setup() {
        points = PointFactory.of(ResourceLines.list("/day6.txt"));
        grid = Grid.of(points);
        grid.fillClosest();
    }

    @Test
    void of() {
        final List<Point> points = PointFactory.of(ResourceLines.list("/day6.txt"));
        final Grid grid = Grid.of(points);

        assertEquals(Size.of(11, 11), grid.size());
    }

    @Test
    void notAtEdge() {
        assertEquals(List.of('D', 'E'), grid.enclosedLetters());
    }

    @Test
    void count() {
        assertEquals(9, grid.count('D'));
        assertEquals(17, grid.count('E'));
    }

    @Test
    void countMax() {
        assertEquals(16, grid.countMax(32));
        grid.print();
    }
}