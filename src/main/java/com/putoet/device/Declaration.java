package com.putoet.device;

public abstract class Declaration {
    public abstract void apply(Device device);
    public abstract String name();

    public static Declaration ip(String line) {
        assert line != null && line.matches("#ip \\d+");
        final String[] parts = line.split(" ");

        return new Declaration() {
            @Override
            public void apply(Device device) {
                device.ipRegister(Integer.parseInt(parts[1]));
            }

            @Override
            public String name() {
                return "ip";
            }
        };
    }
}
