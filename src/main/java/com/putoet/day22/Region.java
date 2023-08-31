package com.putoet.day22;

import com.putoet.grid.Point;
import lombok.Getter;

class Region {
    private final Point coordinate;
    private final Calculator calculator;

    @Getter
    private final boolean target;

    private RegionType type = RegionType.UNKNOWN;
    private long geologicIndex = -1;
    private long erosionLevel = -1;

    public Region(Point coordinate, boolean target, Calculator calculator) {
        this.coordinate = coordinate;
        this.target = target;
        this.calculator = calculator;
    }

    public boolean isMouth() {
        return coordinate.equals(Point.ORIGIN);
    }

    public Point coordinate() {
        return coordinate;
    }

    public long geologicIndex() {
        if (geologicIndex == -1)
            geologicIndex = calculator.geologicIndex(this);

        return geologicIndex;
    }

    public long erosionLevel() {
        if (erosionLevel == -1)
            erosionLevel = calculator.erosionLevel(this);

        return erosionLevel;
    }

    public RegionType type() {
        if (type == RegionType.UNKNOWN)
            type = switch ((int) (erosionLevel() % 3)) {
                case 0 -> RegionType.ROCKY;
                case 1 -> RegionType.WET;
                default -> RegionType.NARROW;
            };

        return type;
    }

    public int riskLevel() {
        return type().ordinal();
    }

    public char symbol() {
        if (isMouth()) return 'M';
        if (isTarget()) return 'T';
        return switch (type()) {
            case ROCKY -> '.';
            case WET -> '=';
            case NARROW -> '|';
            case UNKNOWN -> '*';
        };
    }
}
