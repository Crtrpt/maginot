package com.dj.iotlite.capability;

import com.dj.iotlite.CapabilityInterface;
import com.dj.iotlite.annotation.Capability;

@Capability(value="wifi")
public class WifiCapabilityInterfaceImpl implements CapabilityInterface {

    String name="wifi";
    
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
