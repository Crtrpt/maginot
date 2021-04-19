package com.dj.iotlite.capability.mqtt;


import net.crtrpt.mqtt.interception.AbstractInterceptHandler;

public class PublisherListener extends AbstractInterceptHandler {
    @Override
    public String getID() {
        return "111";
    }
}
