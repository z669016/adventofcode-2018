package com.putoet.day8;

import org.jetbrains.annotations.NotNull;

import java.util.*;

class Node {
    private static int seq = 0;
    private final String name;
    private final List<Node> children = new ArrayList<>();
    private final List<Integer> metadata = new ArrayList<>();
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private OptionalInt metadataSum = OptionalInt.empty(); // for memoization
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private OptionalInt value = OptionalInt.empty(); // for memoization

    private Node(String name) {
        this.name = name;
    }

    public static Node newNode() {
        return new Node("NAME-" + seq++);
    }

    public static Node of(@NotNull String line) {
        final var values = Arrays.stream(line.split(" "))
                .map(Integer::parseInt)
                .toList();

        return of(values.iterator());
    }

    private static Node of(Iterator<Integer> values) {
        final var node = newNode();
        final var childCount = values.next();
        final var metadataCount = values.next();

        for (var i = 0; i < childCount; i++)
            node.addChild(of(values));

        for (var i = 0; i < metadataCount; i++)
            node.addMetaData(values.next());

        return node;
    }

    public void addChild(@NotNull Node child) {
        children.add(child);
        metadataSum = value = OptionalInt.empty(); // reset memoized value
    }

    public void addMetaData(int data) {
        metadata.add(data);
        metadataSum = value = OptionalInt.empty(); // reset memoized value
    }

    public List<Node> children() {
        return children;
    }

    public List<Integer> metadata() {
        return metadata;
    }

    public int metaDataSum() {
        if (metadataSum.isEmpty())
            metadataSum = OptionalInt.of(metadata.stream()
                    .mapToInt(i -> i).sum() + children.stream()
                    .mapToInt(Node::metaDataSum)
                    .sum());

        return metadataSum.getAsInt();
    }

    public int value() {
        if (children.isEmpty())
            return metaDataSum();

        if (value.isEmpty())
            value = OptionalInt.of(metadata.stream()
                    .mapToInt(i -> i - 1) // translate meta data into child index
                    .filter(i -> i >= 0 && i < children.size()) // filter indices out of range
                    .map(i -> children.get(i).value()) // get child value
                    .sum()
            );

        return value.getAsInt();
    }

    public void print() {
        print(0);
    }

    private void print(int indent) {
        System.out.println("\t".repeat(indent) + name + " meta " + metadata);
        children.forEach(child -> child.print(indent + 1));
    }
}
