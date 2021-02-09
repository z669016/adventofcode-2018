package com.putoet.day11;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuelGridTest {
    @Test
    void create() {
        final FuelGrid grid = new FuelGrid(18);
//        for (int y = 0; y < 3; y++) {
//            for (int x = 0; x < 3; x++) {
//                System.out.print(grid.get(Point.of( x + 33, y + 45)));
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
    }

    @Test
    void threeByThreeSum() {
        assertEquals(29, new FuelGrid(18).threeByThreeSum(Point.of(33,45)));
        assertEquals(30, new FuelGrid(42).threeByThreeSum(Point.of(21,61)));
    }

    @Test
    void maxThreeByThree() {
        assertEquals(Point.of(33,45), new FuelGrid(18).maxThreeByThree());
        assertEquals(Point.of(21,61), new FuelGrid(42).maxThreeByThree());

    }

    @Test
    void powerLevel() {
        assertEquals(-5, FuelGrid.powerLevel(57, Point.of(122,79)));
        assertEquals(0, FuelGrid.powerLevel(39, Point.of(217,196)));
        assertEquals(4, FuelGrid.powerLevel(71, Point.of(101,153)));
    }

    @Test
    void rackId() {
        assertEquals(13, FuelGrid.rackId(Point.of(3, 7)));
    }

    @Test
    void hundreds() {
        assertEquals(3, FuelGrid.hundreds(12345));
        assertEquals(3, FuelGrid.hundreds(345));
        assertEquals(0, FuelGrid.hundreds(45));
    }
}