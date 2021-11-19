package com.putoet.day25;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.stream.Collectors;

public class Day25 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day25.txt");
        final List<Point4D> points = lines.stream().map(Point4D::of).collect(Collectors.toList());

        part1(points);
    }

    private static void part1(List<Point4D> points) {
        final Galaxy galaxy = new Galaxy().add(points);
        System.out.println("The galaxy consists of " + galaxy.size() + " constellations.");
    }
}
