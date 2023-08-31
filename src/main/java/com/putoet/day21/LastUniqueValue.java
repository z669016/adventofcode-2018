package com.putoet.day21;

import com.putoet.device.Debug;
import com.putoet.device.Device;

import java.util.HashSet;
import java.util.Set;

class LastUniqueValue extends Debug {
    private final Set<Long> values = new HashSet<>();
    private long lastValue = -1;

    @Override
    public boolean test(Device device) {
        if (enabled()) {
            final var r4 = device.register(4);
            if (lastValue != -1 && values.contains(r4))
                return false;

            lastValue = r4;
            values.add(lastValue);
            System.out.print(values.size() + "\r");
            return true;
        }

        return false;
    }

    public long lastValue() {
        return lastValue;
    }
}
