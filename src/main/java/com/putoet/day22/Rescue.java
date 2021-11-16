package com.putoet.day22;

import com.putoet.grid.Point;

import java.util.*;

public class Rescue {
    private static final List<Point> DIRECTION = Point.directions(true);
    private static final Map<RegionType, Set<Tool>> validTools = Map.of(
            RegionType.ROCKY, Set.of(Tool.CLIMBING_GEAR, Tool.TORCH),
            RegionType.WET, Set.of(Tool.CLIMBING_GEAR, Tool.NEITHER),
            RegionType.NARROW, Set.of(Tool.TORCH, Tool.NEITHER)
    );

    private final Region[][] grid;
    private final Point target;

    public Rescue(Region[][] grid, Point target) {
        this.grid = grid;
        this.target = target;
    }

    public Node initial() {
        return new Node(grid[0][0], Tool.TORCH);
    }

    public List<Node> successors(Node from) {
        final List<Node> successors = new ArrayList<>();

        DIRECTION.forEach(direction -> {
            final Point next = from.region.coordinate().add(direction);
            if (onTheGrid(next) && !from.visited(next)) {
                final Region toRegion = grid[next.y][next.x];
                final Set<Tool> validTools = validToolsFor(from.region, toRegion);
                if (validTools.contains(from.tool))
                    successors.add(new Node(toRegion, from.tool, from.timer + cost(from.tool, from.tool), from));
                else
                    validTools.forEach(tool -> successors.add(new Node(toRegion, tool, from.timer + cost(from.tool, tool), from)));
            }
        });

        return successors;
    }

    public Optional<Node> rescue() {
        final PriorityQueue<Node> frontier = new PriorityQueue<>(grid.length * grid.length);
        final Map<String,Integer> shortestPath = new HashMap<>();
        final Node start = initial();

        frontier.offer(start);
        shortestPath.put(start.key(), start.timer);

        while (!frontier.isEmpty()) {
            final Node node = frontier.poll();

            if (goalTest(node)) {
                return Optional.of(node);
            }

            if (node.timer <= shortestPath.get(node.key())) {
                for (Node neighbour : successors(node)) {
                    final int shortest = shortestPath.getOrDefault(neighbour.key(), Integer.MAX_VALUE);
                    if (neighbour.timer < shortest) {
                        frontier.offer(neighbour);
                        shortestPath.put(neighbour.key(), neighbour.timer);
                    }
                }
            }
        }
        return Optional.empty();
    }

    public boolean goalTest(Node node) {
        return node.region.coordinate().equals(target) && node.tool == Tool.TORCH;
    }

    private int cost(Tool from, Tool to) {
        return 1 + (from == to ? 0 : 7);
    }

    private boolean onTheGrid(Point point) {
        return point.y >= 0 && point.y < grid.length && point.x >= 0 && point.x < grid.length;
    }

    private Set<Tool> validToolsFor(Region from, Region to) {
        //For the target region,only the torch is the valid tool
        if (to.coordinate().equals(target))
            return Set.of(Tool.TORCH);

        // Otherwise,use the intersection for the from region and the tp region
        final Set<Tool> intersection = new HashSet<>(validTools.get(from.type()));
        intersection.retainAll(validTools.get(to.type()));

        return intersection;
    }

    static class Node implements Comparable<Node> {
        public final Region region;
        public final Tool tool;
        public final int timer;
        public final Node parent;

        Node(Region region, Tool tool) {
            this.region = region;
            this.tool = tool;
            this.timer = 0;
            this.parent = null;
        }

        Node(Region region, Tool tool, int timer, Node parent) {
            this.region = region;
            this.tool = tool;
            this.timer = timer;
            this.parent = parent;
        }

        public boolean visited(Point point) {
            Node node = this;
            while (node != null) {
                if (node.region.coordinate().equals(point))
                    return true;

                node = node.parent;
            }

            return false;
        }

        public String key() {
            return region.coordinate().toString() + "|" + tool;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(timer, other.timer);
        }

        public static List<Node> path(Node node) {
            final List<Node> path = new ArrayList<>();
            while (node != null) {
                path.add(node);
                node = node.parent;
            }
            Collections.reverse(path);
            return path;
        }
    }
}

