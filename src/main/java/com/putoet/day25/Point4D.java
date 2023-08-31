package com.putoet.day25;

record Point4D(int x, int y, int z, int q) {

    public int manhattanDistance(Point4D other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z) + Math.abs(q - other.q);
    }

    public static Point4D of(String line) {
        assert line != null;

        final var split = line.split(",");
        assert split.length == 4;

        final int x = Integer.parseInt(split[0].strip());
        final int y = Integer.parseInt(split[1].strip());
        final int z = Integer.parseInt(split[2].strip());
        final int q = Integer.parseInt(split[3].strip());
        return new Point4D(x, y, z, q);
    }
}
