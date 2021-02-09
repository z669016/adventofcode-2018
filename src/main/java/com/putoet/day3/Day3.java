package com.putoet.day3;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args) {
        final List<Claim> claims = ResourceLines.list("/day3.txt").stream()
                .map(Claim::of)
                .collect(Collectors.toList());
        final Fabric fabric = new Fabric(claims);

        part1(fabric);
        part2(claims, fabric);
    }

    private static void part1(Fabric fabric) {
        System.out.println("Overlap is " + fabric.overlap());
    }

    private static void part2(List<Claim> claims, Fabric fabric) {
        System.out.println("Non overlap is " + fabric.nonOverlap(claims));
    }
}
