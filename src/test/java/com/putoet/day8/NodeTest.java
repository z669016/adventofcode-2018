package com.putoet.day8;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void addChild() {
        final var node = Node.newNode();
        assertEquals(0, node.children().size());

        node.addChild(Node.newNode());
        node.addChild(Node.newNode());

        assertEquals(2, node.children().size());
    }

    @Test
    void addMetaData() {
        final var node = Node.newNode();
        assertEquals(0, node.children().size());

        node.addMetaData(3);
        node.addMetaData(5);

        assertEquals(List.of(3, 5), node.metadata());
    }

    @Test
    void metadataSum() {
        final var lines = ResourceLines.list("/day8.txt");
        final var node = Node.of(lines.get(0));
        assertEquals(138, node.metaDataSum());
    }

    @Test
    void value() {
        final var lines = ResourceLines.list("/day8.txt");
        final var node = Node.of(lines.get(0));
        assertEquals(66, node.value());
    }

    @Test
    void of() {
        final var lines = ResourceLines.list("/day8.txt");
        final var node = Node.of(lines.get(0));
        node.print();
    }
}