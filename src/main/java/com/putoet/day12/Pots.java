package com.putoet.day12;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Pots {
    private final int offset;
    private final int zero;
    private final String pots;
    private final Set<String> spread;

    public Pots(@NotNull String pots, @NotNull Set<String> spread) {
        this(0, 0, pots, spread);
    }

    private Pots(int offset, int zero, String pots, Set<String> spread) {
        final var prefix = pots.startsWith(".....") ? "" : ".".repeat(5 - pots.indexOf('#'));
        final var postfix = pots.endsWith(".....") ? "" : ".".repeat(6 - (pots.length() - pots.lastIndexOf('#')));

        this.zero = zero + prefix.length();

        final var sb = new StringBuilder().append(prefix).append(pots).append(postfix);
        while (sb.indexOf("#") > 5) {
            offset++;
            sb.deleteCharAt(0);
        }

        this.offset = offset;
        this.pots = sb.toString();
        this.spread = spread;
    }

    public static Pots of(@NotNull List<String> lines) {
        assert lines.size() > 2;
        final var pots = lines.get(0).substring(15);
        final var spread = lines.stream()
                .skip(2)
                .filter(line -> line.endsWith("#"))
                .map(line -> line.split(" => ")[0])
                .collect(Collectors.toSet());

        return new Pots(pots, spread);
    }

    public Pots next() {
        final var sb = new StringBuilder(".".repeat(pots.length()));

        for (var current = pots.indexOf('#') - 5; current <= pots.lastIndexOf('#'); current++) {
            final var part = pots.substring(current, current + 5);
            if (spread.contains(part))
                sb.setCharAt(current + 2, '#');
        }

        return new Pots(offset, zero, sb.toString(), spread);
    }

    public int zero() {
        return zero;
    }

    public int offset() {
        return offset;
    }

    public int size() {
        return pots.length();
    }

    public Set<String> spread() {
        return spread;
    }

    public long potSum() {
        return potSum(offset);
    }

    public long potSum(long offset) {
        var potSum = 0L;
        for (var i = 0; i < pots.length(); i++) {
            if (pots.charAt(i) == '#')
                potSum += offset + i - zero;
        }

        return potSum;
    }

    @Override
    public String toString() {
        return pots;
    }
}
