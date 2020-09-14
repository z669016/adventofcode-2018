package com.putoet.day3;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FabricTest {
    private List<Claim> claims;
    private Fabric fabric;

    @BeforeEach
    void setup() {
        claims = ResourceLines.list("/day3.txt").stream()
                .map(Claim::of)
                .collect(Collectors.toList());
        fabric = new Fabric(claims);
    }

    @Test
    void of() {
        assertThrows(AssertionError.class, () -> new Fabric(null));
        assertThrows(IllegalArgumentException.class, () -> new Fabric(List.of()));
    }

    @Test
    void overlap() {
        assertEquals(4, fabric.overlap());
    }

    @Test
    void nonOverlap() {
        final List<Claim> claims = fabric.nonOverlap(this.claims);
        assertEquals(1, claims.size());
        assertEquals(3, claims.get(0).id());
    }
}