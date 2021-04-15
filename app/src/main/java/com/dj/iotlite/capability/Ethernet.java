package com.dj.iotlite.capability;

import com.dj.iotlite.CapabilityInterface;
import com.dj.iotlite.annotation.Capability;

@Capability(value = "ethernet")
public class Ethernet implements CapabilityInterface {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    String name="ethernet";

    @Override
    public void run() {

    }

    @Override
    public void setAvailable(boolean isAvailable) {

    }
}
