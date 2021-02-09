package com.putoet.day1;

import com.putoet.resources.ResourceLines;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args) {
        final List<Integer> numbers = ResourceLines.list("/day1.txt")
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        part1(numbers);
        part2(numbers);
    }

    private static void part1(List<Integer> numbers) {
        final int sum = numbers.stream()
                .mapToInt(i -> i)
                .sum();

        System.out.println("The sum of all numbers is " + sum);
    }

    private static void part2(List<Integer> numbers) {
        final Set<Integer> frequencies = new HashSet<>();
        int frequency = 0;
        int i = 0;
        while (frequencies.add(frequency)) {
            frequency += numbers.get(i);
            i = (i + 1) % numbers.size();
        }
        System.out.println("First double frequency is " + frequency);
    }
}
