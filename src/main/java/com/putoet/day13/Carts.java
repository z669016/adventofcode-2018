package com.putoet.day13;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class Carts implements Iterable<Cart> {
    static class CartCrashException extends IllegalStateException {
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

    public static Carts of(@NotNull List<String> lines) {
        final var carts = new TreeSet<Cart>();
        for (var y = 0; y < lines.size(); y++) {
            final var line = lines.get(y);
            for (var x = 0; x < line.length(); x++) {
                final var c = line.charAt(x);
                if ("^>v<".indexOf(c) != -1)
                    carts.add(Cart.of(Point.of(x, y), c));
            }
        }

        return new Carts(carts);
    }

    public Set<Cart> carts() { return carts; }
    public int size() { return carts.size(); }

    public Carts move(@NotNull Tracks tracks) {
        return move(tracks, false);
    }

    public Carts move(@NotNull Tracks tracks, boolean remove) {
        final var from = new TreeSet<>(carts);
        final var to = new TreeSet<Cart>();

        while (!from.isEmpty()) {
            final var cart = from.iterator().next();
            final var next = cart.move(tracks);
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
    public @NotNull Iterator<Cart> iterator() {
        return carts.iterator();
    }

    @Override
    public String toString() {
        return carts.stream()
                .map(Cart::toString)
                .collect(Collectors.joining("\n"));
    }
}
