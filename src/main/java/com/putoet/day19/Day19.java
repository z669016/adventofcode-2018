package com.putoet.day19;

import com.putoet.device.Device;
import com.putoet.resources.ResourceLines;

public class Day19 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        final Device device = Device.of(ResourceLines.list("/day19.txt"));
        device.run();

        System.out.println("value is left in register 0 when the background process halts is " + device.register(0));
    }

    private static void part2() {
        final Device device = Device.of(ResourceLines.list("/day19.txt"));
        device.register(0, 1);
        device.enableVerbose();
        device.run();
        System.out.println("value is left in register 0 when the background process started with reg0 = 1 is " + device.register(0));
    }
}
