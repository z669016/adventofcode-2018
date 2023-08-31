package com.putoet.device;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeclarationTest {

    @Test
    void ip() {
        final var device = mock(Device.class);
        Declaration.ip("#ip 0").accept(device);

        verify(device, times(1)).ipRegister(0);
    }
}