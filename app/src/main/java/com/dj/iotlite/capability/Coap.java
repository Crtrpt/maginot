package com.dj.iotlite.capability;

import com.dj.iotlite.CapabilityInterface;
import com.dj.iotlite.annotation.Capability;

@Capability(value="coap")
public class Coap implements CapabilityInterface {

    String name="coap";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }



    @Override
    public void run() {

    }

    @Override
    public void setAvailable(boolean isAvailable) {

    }
}
