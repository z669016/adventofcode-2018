package com.putoet.day13;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day13 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day13.txt");
        final Tracks tracks = Tracks.of(lines);
        Carts carts = Carts.of(lines);

        partOne(tracks, carts);

        carts = Carts.of(lines);
        partTwo(tracks, carts);
    }

    public static void partOne(Tracks tracks, Carts carts) {
        try {
            while (true)
                carts = carts.move(tracks);
        } catch (Carts.CartCrashException exc) {
            System.out.printf("Cart %s crashed at %s%n", exc.cart().name(), exc.cart().location());
        }
    }

    public static void partTwo(Tracks tracks, Carts carts) {
        while (carts.size() > 1)
            carts = carts.move(tracks, true);

        System.out.println("Last cart is " + carts.carts());
    }
}
