package com.putoet.day8;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day8 {
    public static void main(String[] args) {
        final var node = Node.of(ResourceLines.line("/day8.txt"));

        Timer.run(() -> System.out.println("The total meta data sum is " + node.metaDataSum()));
        Timer.run(() -> System.out.println("The node value value is " + node.value()));
    }
}
