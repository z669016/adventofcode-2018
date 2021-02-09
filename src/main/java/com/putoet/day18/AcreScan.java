package com.putoet.day18;

import com.putoet.grid.Point;

public class AcreScan {
    public final Point location;
    public final int adjacentOpen;
    public final int adjacentTrees;
    public final int adjacentLumberyards;

    public AcreScan(Point location, int adjacentOpen, int adjacentTrees, int adjacentLumberyards) {
        this.location = location;
        this.adjacentOpen = adjacentOpen;
        this.adjacentTrees = adjacentTrees;
        this.adjacentLumberyards = adjacentLumberyards;
    }
}
