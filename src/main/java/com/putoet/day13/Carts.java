package com.putoet.day13;

import utilities.Point;

import java.util.*;
import java.util.stream.Collectors;

public class Carts implements Iterable<Cart> {
    public static class CartCrashException extends IllegalStateException {
        private final Cart cart;

        public CartCrashException(Cart cart) {
            this.cart = cart;
        }

        public Cart cart() { return cart; }
    }



    private final Set<Cart> carts;

    private Carts(Set<Cart> carts) {
        this.carts = carts;
    }

    public static Carts of(List<String> lines) {
        assert lines != null;

        final Set<Cart> carts = new TreeSet<>();
        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                final char c = line.charAt(x);
                if ("^>v<".indexOf(c) != -1)
                    carts.add(Cart.of(Point.of(x, y), c));
            }
        }

        return new Carts(carts);
    }

    public Set<Cart> carts() { return carts; }
    public int size() { return carts.size(); }

    public Carts move(Tracks tracks) {
        return move(tracks, false);
    }

    public Carts move(Tracks tracks, boolean remove) {
        assert tracks != null;

        final Set<Cart> from = new TreeSet<>(carts);
        final Set<Cart> to = new TreeSet<>();

        while (from.size() > 0) {
            final Cart cart = from.iterator().next();
            final Cart next = cart.move(tracks);
            if (from.contains(next) || !to.add(next)) {
                if (!remove)
                    throw new CartCrashException(next);

                from.remove(next);
                to.remove(next);
            }
            from.remove(cart);
        }

        return new Carts(to);
    }

    @Override
    public Iterator<Cart> iterator() {
        return carts.iterator();
    }

    @Override
    public String toString() {
        return carts.stream()
                .map(Cart::toString)
                .collect(Collectors.joining("\n"));
    }
}
