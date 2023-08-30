package com.putoet.day1;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.HashSet;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        final var numbers = ResourceLines.stream("/day1.txt").map(Integer::parseInt).toList();

        Timer.run(() -> part1(numbers));
        Timer.run(() -> part2(numbers));
    }

    private static void part1(List<Integer> numbers) {
        final var sum = numbers.stream()
                .mapToInt(i -> i)
                .sum();

        System.out.println("The sum of all numbers is " + sum);
    }

    private static void part2(List<Integer> numbers) {
        final var frequencies = new HashSet<Integer>();
        var frequency = 0;
        var i = 0;
        while (frequencies.add(frequency)) {
            frequency += numbers.get(i);
            i = (i + 1) % numbers.size();
        }
        System.out.println("First double frequency is " + frequency);
    }
}
