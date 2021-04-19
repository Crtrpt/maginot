package com.dj.iotlite.capability;

import com.dj.iotlite.annotation.Capability;

@Capability(value="power")
public class Power implements CapabilityInterface {

    String name="power";

    @Override
    public String getName() {
        return name;
    }

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
