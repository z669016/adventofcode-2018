package com.putoet.day22;

import com.putoet.grid.Point;

import java.util.*;

class Rescue {
    private static final List<Point> DIRECTION = Point.directions(true);

    private static final Tool[][] validTools;
    static {
        validTools = new Tool[RegionType.values().length][];
        validTools[RegionType.ROCKY.ordinal()] = new Tool[]{Tool.CLIMBING_GEAR, Tool.TORCH};
        validTools[RegionType.WET.ordinal()] = new Tool[]{Tool.CLIMBING_GEAR, Tool.NEITHER};
        validTools[RegionType.NARROW.ordinal()] = new Tool[]{Tool.TORCH, Tool.NEITHER};
        validTools[RegionType.UNKNOWN.ordinal()] = new Tool[]{};
    }

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
        final var successors = new ArrayList<Node>();

        DIRECTION.forEach(direction -> {
            final var next = from.region.coordinate().add(direction);
            if (onTheGrid(next) && !from.visited(next)) {
                final var toRegion = grid[next.y()][next.x()];
                final var validTools = validToolsFor(from.tool, from.region, toRegion);

                for(var tool : validTools)
                    successors.add(new Node(toRegion, tool, from.timer + cost(from.tool, tool), from));
            }
        });

        return successors;
    }

    public Optional<Node> rescue() {
        final var frontier = new PriorityQueue<Node>(grid.length * grid.length);
        final var shortestPath = new HashMap<String,Integer>();
        final var start = initial();

        frontier.offer(start);
        shortestPath.put(start.key(), start.timer);

        while (!frontier.isEmpty()) {
            final var node = frontier.poll();

            if (goalTest(node)) {
                return Optional.of(node);
            }

            if (node.timer <= shortestPath.get(node.key())) {
                for (var neighbour : successors(node)) {
                    final var shortest = shortestPath.getOrDefault(neighbour.key(), Integer.MAX_VALUE);
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
        return point.y() >= 0 && point.y() < grid.length && point.x() >= 0 && point.x() < grid.length;
    }

    private Tool[] validToolsFor(Tool current, Region from, Region to) {
        //For the target region,only the torch is the valid tool
        if (to.coordinate().equals(target))
            return new Tool[] {Tool.TORCH};

        final var toTools = validTools[to.type().ordinal()];
        final var fromTools = validTools[from.type().ordinal()];

        // If the regions are the same, the current tool is the only
        // valid tool as switching would take additional time
        if (containsTool(current, toTools))
            return new Tool[]{current};

        // Otherwise, use the intersection for the from-region and the t0-region
        return Arrays.stream(toTools)
                .filter(tool -> containsTool(tool, fromTools))
                .toArray(Tool[]::new);
    }

    private boolean containsTool(Tool tool, Tool[] tools) {
        if (tools.length == 0)
            return false;

        if (tools.length == 1)
            return tools[0] == tool;

        return tools[0] == tool || tools[1] == tool;
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
    }
}

