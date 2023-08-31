package com.putoet.device;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public abstract class Declaration implements Consumer<Device> {
    public static Declaration ip(@NotNull String line) {
        assert line.matches("#ip \\d+");
        final var parts = line.split(" ");

        return new Declaration() {
            @Override
            public void accept(Device device) {
                device.ipRegister(Integer.parseInt(parts[1]));
            }

            @Override
            public String name() {
                return "ip";
            }
        };
    }

    public abstract String name();

    @Override
    public abstract void accept(Device device);
}
