package com.putoet.day20;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class GridFactory {
    public static Grid of(@NotNull String regex) {
        assert regex.charAt(0) == '^';
        assert regex.charAt(regex.length() - 1) == '$';

        return grid(parse(regex.substring(1, regex.length() - 1)));
    }

    private static Map<Point,Cell> parse(final String regex) {
        final var map = new HashMap<Point,Cell>();
        final var start = new Start();
        map.put(start.location,start);
        parse(0, map, start.location, regex);

        return map;
    }

    private static Point parse(int level, final Map<Point,Cell> map, Point point, final String regex) {
        var offset = 0;
        while (offset < regex.length()) {
            if (regex.charAt(offset) == '(') {
                final var end = choiceEnd(offset, regex);
                final var regexes = choiceSplit(offset, end, regex);
                final var endPoints = parse(level+ 1, map, point, regexes);
                for (var next : endPoints) {
                    parse(level + 1, map, next, regex.substring(end + 1));
                }
                break;
            }
            else {
                point = switch (regex.charAt(offset)) {
                    case 'N' -> move(map, point, Point.NORTH);
                    case 'E' -> move(map, point, Point.EAST);
                    case 'S' -> move(map, point, Point.SOUTH);
                    case 'W' -> move(map, point, Point.WEST);
                    default -> throw new IllegalStateException("Unexpected value: " + regex.charAt(offset));
                };
            }

            offset++;
        }

          System.out.print("Parsed level " + level + ", map size is " + map.size() + ", regex length was " + regex.length() + "\r");

        return point;
    }

    private static Set<Point> parse(int level, final Map<Point,Cell> map, Point point, final List<String> regexes) {
        final var endPoints = new HashSet<Point>();

        for (var choice : regexes) {
            endPoints.add(parse(level, map, point, choice));
        }

        return endPoints;
    }

    private static Point move(Map<Point,Cell> locations, Point point, Point direction) {
        point = point.add(direction);
        locations.put(point, new Door(point, direction));
        point = point.add(direction);
        locations.put(point, new Room(point));
        return point;
    }

    private static int choiceEnd(int offset, String regex) {
        assert offset >= 0 && offset < regex.length();
        assert regex.charAt(offset) == '(';

        var cnt = 0;
        offset++;
        while (!(regex.charAt(offset) == ')' && cnt == 0)) {
            if (regex.charAt(offset) == '(')
                cnt++;
            else if (regex.charAt(offset) == ')')
                cnt--;

            offset++;
        }

        return offset;
    }

    private static List<String> choiceSplit(int start, int end, String regex) {
        assert regex.charAt(start) == '(';
        assert regex.charAt(end) == ')';

        final var regexes = new ArrayList<String>();

        var offset = start + 1;
        var cnt = 0;

        while (offset < end) {
            if (regex.charAt(offset) == '(')
                cnt++;
            else if (regex.charAt(offset) == ')')
                cnt--;
            else if (regex.charAt(offset) == '|' && cnt == 0) {
                regexes.add(regex.substring(start + 1, offset));
                start = offset;
            }

            offset++;
        }

        regexes.add(regex.substring(start + 1, offset));
        return regexes;
    }

    private static Grid grid(Map<Point, Cell> locations) {
        assert !locations.isEmpty();

        final var minX = locations.keySet().stream().mapToInt(Point::x).min().orElseThrow();
        final var minY = locations.keySet().stream().mapToInt(Point::y).min().orElseThrow();
        final var maxX = locations.keySet().stream().mapToInt(Point::x).max().orElseThrow();
        final var maxY = locations.keySet().stream().mapToInt(Point::y).max().orElseThrow();

        final var height = Math.abs(maxY - minY) + 3;
        final var width = Math.abs(maxX - minX) + 3;
        final var grid = new char[height][width];
        for (var line :grid) {
            Arrays.fill(line, '#');
        }

        locations.values().forEach(cell -> grid[height - 2 - cell.location.y() + minY][cell.location.x() - minX + 1] = cell.symbol());

        return new Grid(grid);
    }
}
