package com.putoet.day23;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NanoBotTest {

    @Test
    void of() {
        final NanoBot bot = NanoBot.of("pos=<58901937,-1840529,45022137>, r=72434972");
        assertEquals(58901937, bot.coordinate.x());
        assertEquals(-1840529, bot.coordinate.y());
        assertEquals(45022137, bot.coordinate.z());
        assertEquals(72434972, bot.r);
    }
}