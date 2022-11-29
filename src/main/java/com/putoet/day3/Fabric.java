package com.putoet.day3;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Fabric {
    private final int[][] fabric;

    public Fabric(List<Claim> claims) {
        assert claims != null;

        final OptionalInt dy = claims.stream().mapToInt(c -> c.at().y() + c.size().dy()).max();
        final OptionalInt dx = claims.stream().mapToInt(c -> c.at().x() + c.size().dx()).max();

        if (dy.isEmpty() || dx.isEmpty())
            throw new IllegalArgumentException("Cannot create Fabric for " + claims);

        fabric = new int[dy.getAsInt()][dx.getAsInt()];

        claims.forEach(this::claim);
    }

    public void claim(Claim claim) {
        for (int y = 0; y < claim.size().dy(); y++)
            for (int x = 0; x < claim.size().dx(); x++) {
                if (fabric[y + claim.at().y()][x + claim.at().x()] == 0)
                    fabric[y + claim.at().y()][x + claim.at().x()] = claim.id();
                else
                    fabric[y + claim.at().y()][x + claim.at().x()] = -1;
            }
    }

    public long overlap() {
        return Arrays.stream(fabric).flatMapToInt(Arrays::stream).filter(i -> i == -1).count();
    }

    public List<Claim> nonOverlap(List<Claim> claims) {
        return claims.stream()
                .filter(this::hasNoOverlap)
                .collect(Collectors.toList());
    }

    private boolean hasNoOverlap(Claim claim) {
        for (int y = 0; y < claim.size().dy(); y++)
            for (int x = 0; x < claim.size().dx(); x++) {
                if (fabric[y + claim.at().y()][x + claim.at().x()] != claim.id())
                    return false;
            }

        return true;
    }
}
