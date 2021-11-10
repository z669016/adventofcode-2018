package com.putoet.day15;

import com.putoet.grid.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {
    private Unit unit;

    @BeforeEach
    void setup() {
        unit = new Elf(Point.of(2,1));
    }

    @Test
    void create() {
        assertEquals(Point.of(2,1), unit.position());
    }

    @Test
    void symbol() {
        assertEquals(Elf.SYMBOL, unit.symbol());
    }

    @Test
    void enemy() { assertEquals(Goblin.SYMBOL, unit.enemy());}
}