package com.putoet.day8;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day8.txt");
        final Node node = Node.of(lines.get(0));

        part1(node);
        part2(node);
    }

    private static void part1(Node node) {
        System.out.println("The total meta data sum is " + node.metadatasum());
    }

    private static void part2(Node node) {
        System.out.println("The node value value is " + node.value());
    }
}
