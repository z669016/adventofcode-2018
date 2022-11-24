package com.putoet.day13;

import com.putoet.grid.Point;
import com.putoet.utilities.Direction;
import com.putoet.utilities.DirectionTurn;

import java.util.Objects;
import java.util.function.Supplier;

public class Cart implements Comparable<Cart> {
    private static int id = 1;
    private final String name;
    private final Supplier<DirectionTurn> directionTurnSupplier;
    private final Direction direction;
    private final Point location;

    public static Supplier<DirectionTurn> directionTurnSupplier() {
        return new Supplier<>() {
            private DirectionTurn dt = DirectionTurn.RIGHT;

            @Override
            public DirectionTurn get() {
                return dt = dt.next();
            }
        };
    }

    public Cart(String name, Direction direction, Supplier<DirectionTurn> directionTurnSupplier, Point location) {
        assert name != null && name.length() > 0;
        assert direction != null;
        assert directionTurnSupplier != null;
        assert location != null;

        this.name = name;
        this.direction = direction;
        this.directionTurnSupplier = directionTurnSupplier;
        this.location = location;
    }

    public static Cart of(Point location, char directionChar) {
        final String name = "CART-" + id++;
        final Direction direction = Direction.of(directionChar);

        return new Cart(name, direction, directionTurnSupplier(), location);
    }


    public String name() {
        return name;
    }

    public Point location() {
        return location;
    }

    public Direction direction() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart cart)) return false;
        return Objects.equals(name, cart.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Cart other) {
        int result = Integer.compare(location.y(), other.location.y());
        if (result == 0)
            result = Integer.compare(location.x(), other.location.x());

        return result;
    }

    public Cart move(Tracks tracks) {
        Direction direction = this.direction;
        int x = location.x();
        int y = location.y();

        final Tracks.TrackElement element = tracks.at(direction, location);
        switch (element) {
            case HLINE:
                x = direction == Direction.WEST ? x - 1 : x + 1;
                break;

            case VLINE:
                y = direction == Direction.NORTH ? y - 1 : y + 1;
                break;

            case TOP_LEFT:
                if (direction == Direction.NORTH) {
                    direction = Direction.EAST;
                    x++;
                } else {
                    direction = Direction.SOUTH;
                    y++;
                }
                break;

            case TOP_RIGHT:
                if (direction == Direction.NORTH) {
                    direction = Direction.WEST;
                    x--;
                } else {
                    direction = Direction.SOUTH;
                    y++;
                }
                break;

            case BOTTOM_LEFT:
                if (direction == Direction.SOUTH) {
                    direction = Direction.EAST;
                    x++;
                } else {
                    direction = Direction.NORTH;
                    y--;
                }
                break;

            case BOTTOM_RIGHT:
                if (direction == Direction.SOUTH) {
                    direction = Direction.WEST;
                    x--;
                } else {
                    direction = Direction.NORTH;
                    y--;
                }
                break;

            case INTERSECTION:
                final DirectionTurn directionTurn = directionTurnSupplier.get();
                direction = turn(directionTurn);
                switch (direction) {
                    case NORTH -> y--;
                    case WEST -> x--;
                    case SOUTH -> y++;
                    case EAST -> x++;
                }
                break;

            default:
                throw new IllegalArgumentException("TrackElement '" + element + "' not handled for " + this);
        }

        return new Cart(name, direction, directionTurnSupplier, Point.of(x, y));
    }

    @Override
    public String toString() {
        return String.format("cart: {name: %s, direction: %s, location: %s}", name, direction, location);
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
