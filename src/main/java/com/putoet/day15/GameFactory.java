package com.putoet.day15;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {
    public static Game of(List<String> map) {
        assert map != null;
        assert map.isEmpty() == false;

        final char grid[][] = new char[map.size()][];
        for (int y = 0; y < map.size(); y++) {
            grid[y] = map.get(y).toCharArray();
        }

        return of(grid);
    }

    public static Game of(char[][] map) {
        assert map != null;
        assert map.length > 0;
        for (char[] chars : map) assert chars.length == map[0].length;

        final int maxY = map.length;
        final int maxX = map[0].length;

        final char[][] grid = new char[maxY][maxX];
        final List<Unit> units = new ArrayList<>();

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                grid[y][x] = map[y][x];
                if (grid[y][x] == Goblin.SYMBOL)
                    units.add(new Goblin(Point.of(x, y)));
                else if (grid[y][x] == Elf.SYMBOL)
                    units.add(new Elf(Point.of(x, y)));
            }
        }

        return new Game(new Grid(grid), units);
    }
}
