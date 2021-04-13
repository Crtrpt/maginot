package com.dj.iotlite;

public class CapabilityWarp {
    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public CapabilityInterface getInstance() {
        return instance;
    }

    public void setInstance(CapabilityInterface instance) {
        this.instance = instance;
    }

    Class clazz=null;
    CapabilityInterface instance=null;
}
