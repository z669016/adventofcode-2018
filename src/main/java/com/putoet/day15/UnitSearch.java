package com.putoet.day15;

import com.putoet.grid.Point;
import com.putoet.search.GenericSearch;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UnitSearch {
    public static List<Point> nextPoints(Unit current, List<Point> targets, BeverageBanditsBoard board) {
        final List<GenericSearch.Node<Point>> nextPoints =
                GenericSearch.bfsAll(current.location(), goalTest(targets), successors(board));

        return nextPoints.stream()
                .map(node -> node.path().get(1))
                .distinct()
                .collect(Collectors.toList());
    }

    public static Predicate<Point> goalTest(List<Point> targets) {
        return targets::contains;
    }

    public static Function<Point, List<Point>> successors(BeverageBanditsBoard board) {
        return board::inRange;
    }
}
