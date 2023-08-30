package com.putoet.day5;

import org.jetbrains.annotations.NotNull;

class Polymers {
    public static String units(@NotNull String polymer) {
        return polymer
                .toLowerCase()
                .chars()
                .distinct()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String reactWithout(@NotNull String polymer, char without) {
        final var temp =
                polymer.replaceAll(String.valueOf(Character.toLowerCase(without)), "")
                        .replaceAll(String.valueOf(Character.toUpperCase(without)), "");
        return react(temp);
    }

    public static String react(@NotNull String polymer) {
        final var temp = new StringBuilder(polymer);
        var i = 0;
        while (i < temp.length() - 1) {
            final var a = temp.charAt(i);
            final var b = temp.charAt(i + 1);
            if (similarType(a, b) && (oppositePolarity(a, b))) {
                temp.delete(i, i + 2);
                if (i > 0) i--;
            } else {
                i++;
            }
        }

        return temp.toString();
    }

    public static boolean similarType(char a, char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }

    public static boolean oppositePolarity(char a, char b) {
        return Character.isLowerCase(a) && Character.isUpperCase(b)
                || Character.isLowerCase(b) && Character.isUpperCase(a);
    }
}
