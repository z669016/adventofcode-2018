package com.putoet.day3;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day3 {
    public static void main(String[] args) {
        final var claims = ResourceLines.stream("/day3.txt")
                .map(Claim::of)
                .toList();
        final Fabric fabric = new Fabric(claims);

        Timer.run(() -> System.out.println("Overlap is " + fabric.overlap()));
        Timer.run(() -> System.out.println("Non overlap is " + fabric.nonOverlap(claims)));
    }
}
