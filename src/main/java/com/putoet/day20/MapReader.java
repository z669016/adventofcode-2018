package com.putoet.day20;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import com.putoet.grid.Points;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class MapReader {
    public static Grid of(String regex) {
        return of(Token.of(regex));
    }

    protected static Grid of(Token token) {
        assert token instanceof CompositeElement;

        Stack<Point> current = new Stack<>();
        current.push(Point.ORIGIN);

        final Set<Point> rooms = new HashSet<>(current);

        final Iterator<Token> iter = ((CompositeElement) token).iterator();
        while (iter.hasNext()) {
            token = iter.next();
            current = updateRoomsForToken(token, current, rooms);
        }

        return asGrid(rooms);

    }

    protected static Stack<Point> updateRoomsForToken(Token token, Stack<Point> current, Set<Point> rooms) {
        if (token instanceof Element)
            return updateRoomsForElement( (Element) token, current, rooms);

        if (token instanceof Choice)
            return updateRoomsForChoice( (Choice) token, current, rooms);

        if (token instanceof CompositeElement)
            return updateRoomsForCompositeElement( (CompositeElement) token, current, rooms);

        throw new IllegalStateException("Invalid token type '" + token.getClass().getName());
    }

    protected static Stack<Point> updateRoomsForChoice(Choice choice, Stack<Point> current, Set<Point> rooms) {
        final Stack<Point> end = new Stack<>();
        for (int idx = 0; idx < choice.elementCount(); idx++) {
            final Token token = choice.get(idx);
            end.addAll(updateRoomsForToken(token, current, rooms));
        }
        return end;
    }

    protected static Stack<Point> updateRoomsForCompositeElement(CompositeElement compositeElement, Stack<Point> current, Set<Point> rooms) {
        return null;
    }

    protected static Stack<Point> updateRoomsForElement(Element element, Stack<Point> current, Set<Point> rooms) {
        final Stack<Point> end = new Stack<>();
        for (Point point : current) {
            for (int offset = 0; offset < element.get().length(); offset++) {
                point = switch (element.get().charAt(offset)) {
                    case 'N' -> point.add(Point.of(0, -2));
                    case 'E' -> point.add(Point.of(2, 0));
                    case 'S' -> point.add(Point.of(0, 2));
                    case 'W' -> point.add(Point.of(-2, 0));
                    default -> throw new IllegalStateException("Invalid direction in element '" + element.get() + "'");
                };
                rooms.add(point);
            }
            end.push(point);
        }

        return end;
    }

    protected static Grid asGrid(Set<Point> rooms) {
        assert rooms.size() > 0;

        final Point topLeft = Points.topLeft(rooms);
        final Point bottomRight = Points.bottomRight(rooms);

        final Grid grid = new Grid(topLeft.x - 1, bottomRight.x + 2, topLeft.y - 1, bottomRight.y + 2,
                GridUtils.of(topLeft.x - 1, bottomRight.x + 2, topLeft.y - 1, bottomRight.y + 2, '?'));

        for (int x = topLeft.x - 1; x < bottomRight.x + 2; x++) {
            grid.set(x, topLeft.y - 1, '#');
            grid.set(x, bottomRight.y + 1, '#');
        }

        for (int y = topLeft.y - 1; y < bottomRight.y + 2; y++) {
            grid.set(topLeft.x - 1, y, '#');
            grid.set(bottomRight.x + 1, y, '#');
        }

        rooms.forEach(p -> grid.set(p.x, p.y, '.'));


        return grid;
    }
}
