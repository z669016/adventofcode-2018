package com.putoet.device;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeclarationTest {

    @Test
    void ip() {
        final Device device = mock(Device.class);
        Declaration.ip("#ip 0").apply(device);

        verify(device, times(1)).ipRegister(0);
    }
}