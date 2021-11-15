package com.putoet.device;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {
    private Device device;

    @BeforeEach
    void setup() {
        device = Device.of(ResourceLines.list("/device.txt"));
    }

    @Test
    void run() {
        device.addBreakpoint(-1, new Debug() {
            @Override
            public boolean test(Device device) {
                System.out.printf("ip=%d %s %s%n", device.ip(), device.regs(), device.instruction());
                return true;
            }
        });

        device.run();
        assertEquals(7, device.register(0));
        assertEquals(5, device.register(1));
        assertEquals(6, device.register(2));
        assertEquals(0, device.register(3));
        assertEquals(0, device.register(4));
        assertEquals(9, device.register(5));
    }

    @Test
    void program() {
        assertEquals(7, device.program().size());
    }

    @Test
    void debug() {
        device.addBreakpoint(2, new Debug() {
            @Override
            public boolean test(Device device) {
                return false;
            }
        });

        device.run();
        assertEquals(2, device.register(0));
        assertEquals(5, device.register(1));
        assertEquals(6, device.register(2));
        assertEquals(0, device.register(3));
        assertEquals(0, device.register(4));
        assertEquals(0, device.register(5));
    }
}