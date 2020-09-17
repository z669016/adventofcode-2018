package com.putoet.day5;

public class Polymers {
    public static String units(String polymer) {
        return polymer
                .toLowerCase()
                .chars()
                .distinct()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String reactWithout(String polymer, char without) {
        final String temp =
                polymer.replaceAll(String.valueOf(Character.toLowerCase(without)), "")
                .replaceAll(String.valueOf(Character.toUpperCase(without)), "");
        return react(temp);
    }

    public static String react(String polymer) {
        final StringBuilder temp = new StringBuilder(polymer);
        int i = 0;
        while (i < temp.length() - 1) {
            char a = temp.charAt(i);
            char b = temp.charAt(i + 1);
            if (similarType(a, b) && (oppositePolarity(a, b))) {
                temp.delete(i, i+2);
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
