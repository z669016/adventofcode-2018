package com.putoet.day13;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

record Cart(String name, Direction direction, Supplier<DirectionTurn> directionTurnSupplier,
            Point location) implements Comparable<Cart> {
    private static int id = 1;

    public static Cart of(@NotNull Point location, char directionChar) {
        final var name = "CART-" + id++;
        final var direction = Direction.of(directionChar);
        final var turnSupplier = new Supplier<DirectionTurn>() {
            private DirectionTurn dt = DirectionTurn.RIGHT;

            @Override
            public DirectionTurn get() {
                return dt = dt.next();
            }

            @Override
            public String toString() {
                return "Supplier<DirectionTurn>[dt=" + dt + "]";
            }
        };

        return new Cart(name, direction, turnSupplier, location);
    }

    @Override
    public int compareTo(@NotNull Cart other) {
        return location.compareTo(other.location);
    }

    public Cart move(@NotNull Tracks tracks) {
        var direction = this.direction;
        var x = location.x();
        var y = location.y();

        final var element = tracks.at(direction, location);
        switch (element) {
            case HORIZONTAL_LINE -> x = direction == Direction.WEST ? x - 1 : x + 1;
            case VERTICAL_LINE -> y = direction == Direction.NORTH ? y - 1 : y + 1;
            case TOP_LEFT -> {
                if (direction == Direction.NORTH) {
                    direction = Direction.EAST;
                    x++;
                } else {
                    direction = Direction.SOUTH;
                    y++;
                }
            }
            case TOP_RIGHT -> {
                if (direction == Direction.NORTH) {
                    direction = Direction.WEST;
                    x--;
                } else {
                    direction = Direction.SOUTH;
                    y++;
                }
            }
            case BOTTOM_LEFT -> {
                if (direction == Direction.SOUTH) {
                    direction = Direction.EAST;
                    x++;
                } else {
                    direction = Direction.NORTH;
                    y--;
                }
            }
            case BOTTOM_RIGHT -> {
                if (direction == Direction.SOUTH) {
                    direction = Direction.WEST;
                    x--;
                } else {
                    direction = Direction.NORTH;
                    y--;
                }
            }
            case INTERSECTION -> {
                final var directionTurn = directionTurnSupplier.get();
                direction = turn(directionTurn);
                switch (direction) {
                    case NORTH -> y--;
                    case WEST -> x--;
                    case SOUTH -> y++;
                    case EAST -> x++;
                }
            }
            default -> throw new IllegalArgumentException("TrackElement '" + element + "' not handled for " + this);
        }

        return new Cart(name, direction, directionTurnSupplier, Point.of(x, y));
    }

    private Direction turn(DirectionTurn turn) {
        return switch (turn) {
            case LEFT -> switch (direction) {
                case NORTH -> Direction.WEST;
                case WEST -> Direction.SOUTH;
                case SOUTH -> Direction.EAST;
                case EAST -> Direction.NORTH;
            };

            case RIGHT -> switch (direction) {
                case NORTH -> Direction.EAST;
                case EAST -> Direction.SOUTH;
                case SOUTH -> Direction.WEST;
                case WEST -> Direction.NORTH;
            };

            default -> direction;
        };
    }
}
