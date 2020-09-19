package com.putoet.day9;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void next() {
        final Stock stock = new Stock(5);
        int value = 1;
        while (stock.hasNext()) {
            assertEquals(value++, stock.next());
        }
        assertEquals(6, value);
        assertThrows(NoSuchElementException.class, stock::next);
    }
}