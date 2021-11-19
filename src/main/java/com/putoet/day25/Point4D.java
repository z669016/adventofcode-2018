package com.putoet.day25;

public class Point4D {
    public static Point4D ORIGIN = new Point4D(0, 0, 0, 0);

    public final int x, y, z, q;

    public Point4D(int x, int y, int z, int q) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.q = q;
    }

    public int manhattanDistance() {
        return manhattanDistance(ORIGIN);
    }

    public int manhattanDistance(Point4D other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z) + Math.abs(q - other.q);
    }

    public static Point4D of(String line) {
        assert line != null;

        final String split[] = line.split(",");
        assert split.length == 4;

        final int x = Integer.parseInt(split[0].strip());
        final int y = Integer.parseInt(split[1].strip());
        final int z = Integer.parseInt(split[2].strip());
        final int q = Integer.parseInt(split[3].strip());
        return new Point4D(x, y, z, q);
    }
}
