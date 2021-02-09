package com.putoet.day12;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Pots {
    private final int offset;
    private final int zero;
    private final String pots;
    private final Set<String> spread;

    public Pots(String pots, Set<String> spread) {
        this(0, 0, pots, spread);
    }

    private Pots(int offset, int zero, String pots, Set<String> spread) {
        final String prefix = pots.startsWith(".....") ? "" : ".".repeat(5 - pots.indexOf('#'));
        final String postfix = pots.endsWith(".....") ? "" : ".".repeat(6 - (pots.length() - pots.lastIndexOf('#')));

        this.zero = zero + prefix.length();
        final StringBuilder sb = new StringBuilder().append(prefix).append(pots).append(postfix);
        while (sb.indexOf("#") > 5) {
            offset++;
            sb.deleteCharAt(0);
        }
        this.offset = offset;
        this.pots = sb.toString();
        this.spread = spread;
    }

    public static Pots of(List<String> lines) {
        assert lines != null && lines.size() > 2;
        final String pots = lines.get(0).substring(15);
        final Set<String> spread = lines.stream()
                .skip(2)
                .filter(line -> line.endsWith("#"))
                .map(line -> line.split(" => ")[0])
                .collect(Collectors.toSet());

        return new Pots(pots, spread);
    }

    public Pots next() {
        final StringBuilder sb = new StringBuilder(".".repeat(pots.length()));

        for (int current = pots.indexOf('#') - 5; current <= pots.lastIndexOf('#'); current++) {
            final String part = pots.substring(current, current + 5);
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
        long potSum = 0;
        for (int i = 0; i < pots.length(); i++) {
            if (pots.charAt(i) == '#')
                potSum += offset + i - zero;
        }

        return potSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pots)) return false;
        Pots pots1 = (Pots) o;
        return offset == pots1.offset &&
                zero == pots1.zero &&
                Objects.equals(pots, pots1.pots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset, zero, pots);
    }

    @Override
    public String toString() {
        return pots;
    }
}
