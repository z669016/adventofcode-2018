package com.putoet.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Routes {
    private static final char OPEN = '(';
    private static final char CLOSE = ')';
    private static final char OPTION = '|';

    public static List<Route> of(String regex) {
        assert regex != null && regex.startsWith("^") && regex.endsWith("$");
        final List<String> routes = decode(regex.substring(1, regex.length() - 1));
        return routes.stream().map(Route::new).collect(Collectors.toList());
    }

    private static List<String> decode(String encodedRoutes) {
        List<String> routes = new ArrayList<>();
        for (String split : split(encodedRoutes)) {
            if (routes.size() == 0)
                routes = add(routes, split);
            else
                routes = append(routes, split);
        }

        return routes;
    }

    private static List<String> append(List<String> routes, String encodedRoute) {
        if (!isSplittable(encodedRoute)) {
            for (int x = 0; x < routes.size(); x++)
                routes.set(x, routes.get(x) + encodedRoute);
            return routes;
        }

        final List<String> split = split(encodedRoute);
        final List<String> newRoutes = new ArrayList<>();
        for (String route : routes) {
            for (String s : split) {
                newRoutes.add(route + s);
            }
        }
        return newRoutes;
    }

    private static List<String> add(List<String> routes, String split) {
        if (!isSplittable(split))
            routes.add(split);
        else
            routes.addAll(decode(split));

        return routes;
    }

    private static boolean isSplittable(String encodedRoutes) {
        return encodedRoutes.indexOf(OPEN) != -1;
    }

    private static boolean isChoice(String encodedRoutes) {
        return encodedRoutes.charAt(0) == OPEN && encodedRoutes.charAt(encodedRoutes.length() - 1) == CLOSE;
    }

    protected static List<String> split(String encodedRoutes) {
        if (isChoice(encodedRoutes))
            return splitChoice(encodedRoutes);

        final List<String> split = new ArrayList<>();
        while (isSplittable(encodedRoutes)) {
            final int start = encodedRoutes.indexOf(OPEN);
            if (start > 1) {
                split.add(encodedRoutes.substring(0, start));
                encodedRoutes = encodedRoutes.substring(start);
            }

            final int close = findClose(encodedRoutes);
            split.add(encodedRoutes.substring(0, close + 1));
            encodedRoutes = encodedRoutes.substring(close + 1);
        }

        split.add(encodedRoutes);
        return split;
    }

    private static List<String> splitChoice(String encodedRoutes) {
        assert isChoice(encodedRoutes);

        final List<String> split = new ArrayList<>();

        encodedRoutes = encodedRoutes.substring(1, encodedRoutes.length() - 1);
        int indent = 0;
        int offset = 0;

        while (offset < encodedRoutes.length()) {
            if (encodedRoutes.charAt(offset) == OPEN)
                indent++;
            else if (encodedRoutes.charAt(offset) == CLOSE)
                indent--;
            else if (encodedRoutes.charAt(offset) == OPTION && indent == 0) {
                split.add(encodedRoutes.substring(0, offset));
                encodedRoutes = encodedRoutes.substring(offset + 1);
                offset = -1;
            }
            offset++;
        }

        split.add(encodedRoutes);
        return split;
    }

    private static int findClose(String encodedRoutes) {
        int indent = 1;
        int offset = 1;
        while (indent != 0) {
            switch (encodedRoutes.charAt(offset)) {
                case OPEN -> indent++;
                case CLOSE -> indent--;
            }
            offset++;
        }

        return offset - 1;
    }
}
