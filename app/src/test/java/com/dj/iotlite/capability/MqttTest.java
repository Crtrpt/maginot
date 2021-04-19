package com.dj.iotlite.capability;

import org.junit.Test;

import java.io.IOException;

public class MqttTest {

    @Test
    public void run() throws IOException {
        CapabilityInterface c=new Mqtt();
        c.run();
    }
}