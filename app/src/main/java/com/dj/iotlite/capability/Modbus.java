package com.dj.iotlite.capability;

import android.util.Log;

import com.dj.iotlite.annotation.Capability;

@Capability(value="mqtt")
public class Modbus implements CapabilityInterface {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    String name;

    @Override
    public void run() {
        Log.d(this.getClass().getName(), "启动modbus");

    }

    @Override
    public void setAvailable(boolean isAvailable) {

    }
}
