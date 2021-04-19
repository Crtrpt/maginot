package com.dj.iotlite.capability;

import android.util.Log;

import com.dj.iotlite.annotation.Capability;

@Capability(value="gate")
public class Gate implements CapabilityInterface {

    String name="gate";


    @Override
    public void init() {
        Log.d(this.getClass().getName(), "启动网关");
    }



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
        if(isAvailable){
            this.init();
        }else {
            this.stop();
        }
    }
}
