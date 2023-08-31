package com.putoet.day23;

import com.putoet.grid.Point3D;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

record NanoBot(Point3D coordinate, int r) {
    //pos=<58901937,1840529,45022137>, r=72434972
    private static final Pattern PATTERN =Pattern.compile("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(\\d+)");

    public static NanoBot of(String line) {
        final Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Line cannot be transformed into a NanoBot: " + line);

        final int x = Integer.parseInt(matcher.group(1));
        final int y = Integer.parseInt(matcher.group(2));
        final int z = Integer.parseInt(matcher.group(3));
        final int r = Integer.parseInt(matcher.group(4));
        return new NanoBot(Point3D.of(x, y, z), r);
    }
}
