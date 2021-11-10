package com.putoet.day15;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitImplTest {
    private static final List<Unit> units = List.of(
            new Goblin(Point.of(2, 1)),
            new Elf(Point.of(4, 2)),
            new Goblin(Point.of(5, 2)),
            new Goblin(Point.of(5, 3)),
            new Goblin(Point.of(3, 4)),
            new Elf(Point.of(5, 4))
    );

    @Test
    void targets() {
        assertEquals(units.get(0).targets(units), List.of(
                new Elf(Point.of(4, 2)),
                new Elf(Point.of(5, 4))
        ));

        assertEquals(units.get(5).targets(units), List.of(
                new Goblin(Point.of(2, 1)),
                new Goblin(Point.of(5, 2)),
                new Goblin(Point.of(5, 3)),
                new Goblin(Point.of(3, 4))
        ));
    }


}