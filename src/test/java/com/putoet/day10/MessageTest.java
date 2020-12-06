package com.putoet.day10;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;
import com.putoet.utilities.GridUtils;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void of() {
        final Message message = Message.of(ResourceLines.list("/day10.txt"));

        message.print();
        message.move();
        System.out.println();
        message.print();
        message.move();
        System.out.println();
        message.print();
        message.move();
        System.out.println();
        message.print();
        message.move();
    }

    @Test
    void decrypt() {
        final Message message = Message.of(ResourceLines.list("/day10.txt"));
        final char[][] grid = message.decrypt();

        GridUtils.print(grid);

        assertEquals(3, message.decryptTime());
    }
}