package com.putoet.day10;

import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void decrypt() {
        final var message = Message.of(ResourceLines.list("/day10.txt"));
        final var grid = message.decrypt();

        GridUtils.print(grid);

        assertEquals(3, message.decryptTime());
    }
}