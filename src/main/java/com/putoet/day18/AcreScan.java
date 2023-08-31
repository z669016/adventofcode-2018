package com.putoet.day18;

import com.putoet.grid.Point;

public record AcreScan(Point location, int adjacentOpen, int adjacentTrees, int adjacentLumberyards) {
}
