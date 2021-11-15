package com.putoet.day21;

import com.putoet.device.Debug;
import com.putoet.device.Device;
import com.putoet.resources.ResourceLines;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day21 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day21.txt");
        final long lowerBound = part1(lines);
        part2(lines, lowerBound);
    }

    private static long part1(List<String> lines) {
        final Device device = Device.of(lines);

        device.addBreakpoint(28, new Debug() {
            @Override
            public boolean test(Device device) {
                System.out.println("The value for R0 should be " + device.register(4));
                return false;
            }
        });

        device.run();

        return device.register(4);
    }

    private static void part2(List<String> lines,long lowerBound) {
        final Device device = Device.of(lines);
        final LastUniqueValue lastUniqueValue = new LastUniqueValue();
        device.addBreakpoint(28, lastUniqueValue);
        device.run();

        System.out.println("The lowest value with the most instructions is " + lastUniqueValue.lastValue());
    }
}

class LastUniqueValue extends Debug {
    private Set<Long> values= new HashSet<>();
    private long lastValue = -1;

    @Override
    public boolean test(Device device) {
        if (lastValue != -1 && values.contains(device.register(4)))
            return false;

        lastValue = device.register(4);
        values.add(lastValue);
        System.out.print(values.size() + "\r");
        return true;
    }

    public long lastValue() {
        return lastValue;
    }
}