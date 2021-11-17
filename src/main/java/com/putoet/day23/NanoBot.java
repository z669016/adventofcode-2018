package com.putoet.day23;

import com.putoet.grid.Point3D;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NanoBot {
    //pos=<58901937,1840529,45022137>, r=72434972
    private static final Pattern PATTERN =Pattern.compile("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(\\d+)");

    public final Point3D coordinate;
    public final int r;

    private NanoBot(int x,int y,int z,int r) {
        this.coordinate = Point3D.of(x, y, z);
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NanoBot)) return false;
        NanoBot nanoBot = (NanoBot) o;
        return r == nanoBot.r && coordinate.equals(nanoBot.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, r);
    }

    @Override
    public String toString() {
        return "NanoBot{" +
                "coordinate=" + coordinate +
                ", r=" + r +
                '}';
    }

    public static NanoBot of(String line) {
        final Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Line cannot be transformed into a NanoBot: " + line);

        final int x = Integer.parseInt(matcher.group(1));
        final int y = Integer.parseInt(matcher.group(2));
        final int z = Integer.parseInt(matcher.group(3));
        final int r = Integer.parseInt(matcher.group(4));
        return new NanoBot(x, y, z, r);
    }
}
