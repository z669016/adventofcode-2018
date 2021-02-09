package com.putoet.day15;

import com.putoet.grid.Point;

import java.util.*;
import java.util.stream.Collectors;

public class UnitSearch {
    public static class NearestSet extends ArrayList<Node> {
        private int pathLength = Integer.MAX_VALUE;

        @Override
        public boolean add(Node node) {
            if (node.steps() > pathLength)
                return false;

            if (node.steps() < pathLength)
                clear();

            pathLength = node.steps();
            return super.add(node);
        }

        public int pathLength() {
            return pathLength;
        }
    }

    public static class Node implements Comparable<Node> {
        public final Point state;
        public final Node parent;

        public Node(Point state, Node parent) {
            assert state != null;

            this.state = state;
            this.parent = parent;
        }

        public int compareTo(Node other) {
            int compareTo = Integer.compare(state.y, other.state.y);
            if (compareTo == 0)
                compareTo = Integer.compare(state.x, other.state.x);

            return compareTo;
        }

        public List<Point> path() {
            final List<Point> path = new ArrayList<>();
            Node node = this;

            path.add(node.state);
            while (node.parent != null) {
                node = node.parent;
                path.add(0, node.state); // add to front
            }
            return path;
        }

        public int steps() {
            int steps = 0;
            for (Node node = this; node.parent != null; node = node.parent) {
                steps++;
            }
            return steps;
        }

        public String toString() {
            return state.toString() + " -> " + parent;
        }
    }

    public static List<Node> bfs(Point initial, List<Point> targets, BeverageBanditsBoard board) {
        assert initial != null;
        assert targets != null && targets.size() > 0;

        final Queue<Node> frontier = new LinkedList<>();
        frontier.offer(new Node(initial, null));

        final NearestSet nearest = new NearestSet();
        final Set<Point> explored = new HashSet<>();
        explored.add(initial);

        while (!frontier.isEmpty()) {
            final Node currentNode = frontier.poll();
            final Point currentState = currentNode.state;

            if (nearest.size() > 0)
                if (currentNode.steps() > nearest.pathLength())
                    continue;

            if (targets.contains(currentState)) {
                nearest.add(currentNode);
                continue;
            }

            for (Point child : successors(currentState, board)) {
                if (explored.contains(child)) {
                    continue;
                }
                explored.add(child);
                frontier.offer(new Node(child, currentNode));
            }
        }

        nearest.sort(Comparator.naturalOrder());
        return nearest;
    }

    public static List<Point> successors(Point point, BeverageBanditsBoard board) {
        return board.inRange(point);
    }

    public static Optional<Point> nextPointFor(Unit current, BeverageBanditsBoard board) {
        final List<Unit> enemies = board.enemies(current);
        if (enemies.size() == 0)
            return Optional.empty();

        final List<Point> inRange = enemies.stream()
                .map(unit -> board.inRange(unit.location()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        if (inRange.size() == 0)
            return Optional.empty();

        final List<Node> nearest = bfs(current.location(), inRange, board);
        if (nearest.size() == 0)
            return Optional.empty();

        final Point next = firstAfterCurrent(current.location(), nearest);
        return Optional.of(next);
    }

    private static Point firstAfterCurrent(Point current, List<Node> nearest) {
        if (nearest.size() == 1)
            return nearest.get(0).path().get(1);

        if (nearest.get(0).path().get(1).equals(nearest.get(1).path().get(1)))
            return nearest.get(0).path().get(1);

        System.out.println("Moving from " + current);
        nearest.forEach(System.out::println);

//        final Optional<List<Point>> next = nearest.stream()
//                .filter(node -> node.state.y > current.y || (node.state.y == current.y && node.state.x >= current.x))
//                .map(Node::path)
//                .findFirst();

         System.out.println("Selected first step " + nearest.get(0).path().get(1));
//        return next.isPresent() ? next.get().get(1) : nearest.get(0).path().get(1);


        return nearest.get(0).path().get(1);
    }
}
