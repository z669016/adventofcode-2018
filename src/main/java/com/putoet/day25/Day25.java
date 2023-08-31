package com.putoet.day25;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day25 {
    public static void main(String[] args) {
        final var points = ResourceLines.stream("/day25.txt")
                .map(Point4D::of)
                .toList();

        Timer.run(() -> {
            final var galaxy = new Galaxy().add(points);
            System.out.println("The galaxy consists of " + galaxy.size() + " constellations.");
        });
    }
}
