package com.putoet.day15;

import com.putoet.grid.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {
    private Unit unit;

    @BeforeEach
    void setup() {
        unit = new Goblin(Point.of(1,2));
    }

    @Test
    void create() {
        assertEquals(Point.of(1,2),unit.position());
    }

    @Test
    void symbol() {
        assertEquals(Goblin.SYMBOL,unit.symbol());
    }

    @Test
    void enemy() { assertEquals(Elf.SYMBOL, unit.enemy());}
}