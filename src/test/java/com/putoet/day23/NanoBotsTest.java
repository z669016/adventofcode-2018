package com.putoet.day23;

import com.putoet.grid.Point3D;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
        final Optional<NanoBot> bot = NanoBots.strongest(bots);
        assertTrue(bot.isPresent());
        assertEquals(Point3D.ORIGIN, bot.get().coordinate);
        assertEquals(4, bot.get().r);
    }

    @Test
    void inRange() {
        final NanoBot target = NanoBots.strongest(bots).get();
        final List<NanoBot> inRange = NanoBots.inRange(target, bots);
        assertEquals(7, inRange.size());
    }
}