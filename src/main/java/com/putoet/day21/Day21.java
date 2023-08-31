package com.putoet.day21;

import com.putoet.device.Debug;
import com.putoet.device.Device;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day21 {
    public static void main(String[] args) {
        final var lines = ResourceLines.list("/day21.txt");
        Timer.run(() -> part1(lines));
        Timer.run(() -> part2(lines));
    }

    static void part1(List<String> lines) {
        final var device = Device.of(lines);

        device.addBreakpoint(28, new Debug() {
            @Override
            public boolean test(Device device) {
                System.out.println("The value for R0 should be " + device.register(4));
                return false;
            }
        });

        device.run();
    }

    static void part2(List<String> lines) {
        final var device = Device.of(lines);
        final var lastUniqueValue = new LastUniqueValue();
        device.addBreakpoint(28, lastUniqueValue);
        device.run();

        System.out.println("The lowest value with the most instructions is " + lastUniqueValue.lastValue());
    }
}

