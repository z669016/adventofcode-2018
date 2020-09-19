package com.putoet.day9;

public class CircleDoubleLinkedList implements Circle {
    private class Node {
        public final int value;
        public Node next;
        public Node prev;

        private Node(int value) {
            this.value = value;
            this.next = this.prev = this;
        }
    }

    private Node current = new Node(0);

    @Override
    public int place(int marble) {
        int score = 0;
        if (marble % 23 == 0) {
            score += marble;
            counterclockWise();
            score += delete();
        } else {
            clockwise();

            final Node node = new Node(marble);
            insert(node);
        }

        return score;
    }

    private int delete() {
        final int score = current.value;

        current.prev.next = current.next;
        current.next.prev = current.prev;
        current = current.next;

        return score;
    }

    private void insert(Node node) {
        node.prev = current;
        node.next = current.next;

        current.next.prev = node;
        current.next = node;

        current = node;
    }

    private void counterclockWise() {
        for (int idx = 0; idx < 7; idx++)
            current = current.prev;
    }

    private void clockwise() {
        current = current.next;
    }

    @Override
    public int size() {
        Node ptr = current;
        int size = 0;
        do {
            size++;
            ptr = ptr.next;
        } while (ptr.value != current.value);

        return size;
    }

    @Override
    public String toString() {
        Node ptr = current;
        while (ptr.value != 0)
            ptr = ptr.next;

        final StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < size(); idx++, ptr = ptr.next)
            sb.append(ptr.value == current.value ? "(" + ptr.value + ")" : ptr.value).append(" ");

        return sb.toString();
    }
}
