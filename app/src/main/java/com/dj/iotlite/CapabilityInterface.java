package com.dj.iotlite;

public interface CapabilityInterface {

    String getName();

    default String getDesc() {
        return "";
    }

    default String getIcon() {
        return "";
    }

    void setName(String name);

    void run();

    void setAvailable(boolean isAvailable);
}
