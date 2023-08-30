package com.putoet.day3;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

class Fabric {
    private final int[][] fabric;

    public Fabric(@NotNull List<Claim> claims) {
        if (claims.isEmpty())
            throw new IllegalArgumentException();

        final var dy = claims.stream().mapToInt(c -> c.at().y() + c.size().dy()).max().orElseThrow();
        final var dx = claims.stream().mapToInt(c -> c.at().x() + c.size().dx()).max().orElseThrow();

        fabric = new int[dy][dx];

        claims.forEach(this::claim);
    }

    public void claim(@NotNull Claim claim) {
        for (var y = 0; y < claim.size().dy(); y++)
            for (var x = 0; x < claim.size().dx(); x++) {
                if (fabric[y + claim.at().y()][x + claim.at().x()] == 0)
                    fabric[y + claim.at().y()][x + claim.at().x()] = claim.id();
                else
                    fabric[y + claim.at().y()][x + claim.at().x()] = -1;
            }
    }

    public long overlap() {
        return Arrays.stream(fabric).flatMapToInt(Arrays::stream).filter(i -> i == -1).count();
    }

    public List<Claim> nonOverlap(@NotNull List<Claim> claims) {
        return claims.stream()
                .filter(this::hasNoOverlap)
                .toList();
    }

    private boolean hasNoOverlap(Claim claim) {
        for (var y = 0; y < claim.size().dy(); y++)
            for (var x = 0; x < claim.size().dx(); x++) {
                if (fabric[y + claim.at().y()][x + claim.at().x()] != claim.id())
                    return false;
            }

        return true;
    }
}
