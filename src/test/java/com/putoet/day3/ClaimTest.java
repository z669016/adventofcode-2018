package com.putoet.day3;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;
import com.putoet.grid.Size;

import static org.junit.jupiter.api.Assertions.*;

class ClaimTest {

    @Test
    void of() {
        final Claim claim = Claim.of("#3 @ 5,6: 2x4");

        assertEquals(3, claim.id());
        assertEquals(Point.of(5, 6), claim.at());
        assertEquals(new Size(2, 4), claim.size());
    }
}