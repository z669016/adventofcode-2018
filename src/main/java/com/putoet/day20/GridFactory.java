package com.putoet.day20;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;

import java.util.*;

public class GridFactory {
    public static Grid of(String regex) {
        assert regex.charAt(0) == '^';
        assert regex.charAt(regex.length() - 1) == '$';

        final long start = System.currentTimeMillis();
        final Map<Point,Cell> locations = parse(regex.substring(1, regex.length() - 1));
        System.out.println("Map parsed in " + (System.currentTimeMillis()- start) + " ms");

        return grid(locations);
    }

    private static Map<Point,Cell> parse(final String regex) {
        final Map<Point,Cell> map = new HashMap<>();
        final Cell start = new Start();
        map.put(start.location,start);
        parse(0, map, start.location, regex);

        return map;
    }

    private static Point parse(int level, final Map<Point,Cell> map, Point point, final String regex) {
        int offset = 0;
        while (offset < regex.length()) {
            if (regex.charAt(offset) == '(') {
                final int end = choiceEnd(offset, regex);
                final List<String> regexes = choiceSplit(offset, end, regex);
                final Set<Point> endPoints = parse(level+ 1, map, point, regexes);
                for (Point next : endPoints) {
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
        final Set<Point> endPoints = new HashSet<>();

        for (String choice : regexes) {
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

        int cnt = 0;
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

        final List<String> regexes = new ArrayList<>();

        int offset = start + 1;
        int cnt = 0;

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

        final int minX = locations.keySet().stream().mapToInt(Point::x).min().orElseThrow();
        final int minY = locations.keySet().stream().mapToInt(Point::y).min().orElseThrow();
        final int maxX = locations.keySet().stream().mapToInt(Point::x).max().orElseThrow();
        final int maxY = locations.keySet().stream().mapToInt(Point::y).max().orElseThrow();

        final int height = Math.abs(maxY - minY) + 3;
        final int width = Math.abs(maxX - minX) + 3;
        final char[][] grid = new char[height][width];
        for (char[] line :grid) {
            Arrays.fill(line, '#');
        }

        locations.values().forEach(cell -> grid[height - 2 - cell.location.y() + minY][cell.location.x() - minX + 1] = cell.symbol());

        return new Grid(grid);
    }
}
