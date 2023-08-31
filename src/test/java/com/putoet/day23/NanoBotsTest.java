package com.putoet.day23;

import com.putoet.grid.Point3D;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NanoBotsTest {
    private static final List<String> lines = ResourceLines.list("/day23.txt");
    private static final List<NanoBot> bots = NanoBots.of(lines);

    @Test
    void of() {
        assertEquals(9, bots.size());
    }

    @Test
    void strongest() {
        final var bot = NanoBots.strongest(bots).orElseThrow();
        assertEquals(Point3D.ORIGIN, bot.coordinate());
        assertEquals(4, bot.r());
    }

    @Test
    void inRange() {
        final var target = NanoBots.strongest(bots).orElseThrow();
        final var inRange = NanoBots.inRange(target, bots);
        assertEquals(7, inRange.size());
    }
}