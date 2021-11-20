package com.putoet.day15;

import com.putoet.grid.Point;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {
    public static Game of(List<String> lines) {
        final List<Unit> units = new ArrayList<>();
        final char[][] grid = new char[lines.size()][lines.get(0).length()];

        final int lineLength = lines.get(0).length();
        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for(int x = 0; x < line.length(); x++) {
                final char c = line.charAt(x);
                if ("#.EG".indexOf(c) == -1 || line.length() != lineLength)
                    throw new IllegalArgumentException("Invalid board for this game");

                if (c == 'E' || c == 'G')
                    units.add(new Unit(UnitType.of(c), Point.of(x, y)));
                grid[y][x] = c;
            }
        }

        return new Game(grid,units);
    }

    private GameFactory() {}
}
