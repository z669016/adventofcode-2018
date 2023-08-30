package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day13 {
    public static void main(String[] args) {
        final var lines = ResourceLines.list("/day13.txt");

        Timer.run(() -> part1(lines));
        Timer.run(() -> part2(lines));
    }

    public static void part1(List<String> lines) {
        final var tracks = Tracks.of(lines);

        var carts = Carts.of(lines);
        try {
            //noinspection InfiniteLoopStatement
            while (true)
                carts = carts.move(tracks);
        } catch (Carts.CartCrashException exc) {
            System.out.println("Cart crashed " + exc.cart());
        }
    }

    public static void part2(List<String> lines) {
        final var tracks = Tracks.of(lines);
        var carts = Carts.of(lines);

        while (carts.size() > 1)
            carts = carts.move(tracks, true);

        System.out.println("Last cart is " + carts.carts());
    }
}
