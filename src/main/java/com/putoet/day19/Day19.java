package com.putoet.day19;

import com.putoet.device.Device;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day19 {
    public static void main(String[] args) {
        Timer.run(Day19::part1);
        Timer.run(Day19::part2);
    }

    private static void part1() {
        final var device = Device.of(ResourceLines.list("/day19.txt"));
        device.run();

        System.out.println("value is left in register 0 when the background process halts is " + device.register(0));
    }

    private static void part2() {
        int r0 = 0;
        // int r2 = ((19 * 4 * 11) + (6 * 22 + 9)); // part 1
        int r2 = ((19*4*11)+(6*22+9)) + (((30*(29+(27*28)))*14)*32); // part 2
        int r4 = 1;

        do {
            if (r2 % r4 == 0)
                r0 += r4;

            r4 = r4 + 1;
        } while (r4 <= r2);

        System.out.println("value is left in register 0 when the background process halts is " + r0);
    }
}
