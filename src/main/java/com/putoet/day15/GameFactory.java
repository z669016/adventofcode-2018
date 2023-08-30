package com.putoet.day15;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class GameFactory {
    public static Game of(@NotNull List<String> lines) {
        return of(lines, 3);
    }

    public static Game of(@NotNull List<String> lines, int elfAttackPower) {
        final var units = new ArrayList<Unit>();
        final var grid = new char[lines.size()][lines.get(0).length()];

        final var lineLength = lines.get(0).length();
        for (var y = 0; y < lines.size(); y++) {
            final var line = lines.get(y);
            for(var x = 0; x < line.length(); x++) {
                final var c = line.charAt(x);
                if ("#.EG".indexOf(c) == -1 || line.length() != lineLength)
                    throw new IllegalArgumentException("Invalid board for this game");

                if (c == 'E')
                    units.add(new Unit(UnitType.of(c), Point.of(x, y), elfAttackPower));
                else if (c == 'G')
                    units.add(new Unit(UnitType.of(c), Point.of(x, y)));

                grid[y][x] = c;
            }
        }

        return new Game(grid,units);
    }

    private GameFactory() {}
}
