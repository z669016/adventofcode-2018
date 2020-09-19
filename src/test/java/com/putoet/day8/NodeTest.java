package com.putoet.day8;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void addChild() {
        final Node node = Node.newNode();
        assertEquals(0, node.children().size());

        node.addChild(Node.newNode());
        node.addChild(Node.newNode());

        assertEquals(2, node.children().size());
    }

    @Test
    void addMetaData() {
        final Node node = Node.newNode();
        assertEquals(0, node.children().size());

        node.addMetaData(3);
        node.addMetaData(5);

        assertEquals(List.of(3, 5), node.metadata());
    }

    @Test
    void metadataSum() {
        final List<String> lines = ResourceLines.list("/day8.txt");
        final Node node = Node.of(lines.get(0));
        assertEquals(138, node.metadatasum());
    }

    @Test
    void value() {
        final List<String> lines = ResourceLines.list("/day8.txt");
        final Node node = Node.of(lines.get(0));
        assertEquals(66, node.value());
    }

    @Test
    void of() {
        final List<String> lines = ResourceLines.list("/day8.txt");
        final Node node = Node.of(lines.get(0));
        node.print();
    }
}