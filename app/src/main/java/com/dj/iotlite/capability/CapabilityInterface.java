package com.dj.iotlite.capability;

import com.dj.iotlite.capability.Bluetooth;

import java.io.IOException;
import java.util.HashMap;

public interface CapabilityInterface {

    default String getName() {
        return "";
    }

    ;

    default void setName() {
    }

    default String getDesc() {
        return "";
    }

    default String getIcon() {
        return "";
    }

    void setName(String name);

    void run() throws IOException;

    void setAvailable(boolean isAvailable);

    default void init() {
    };

    default void stop() {
    }

    ;
}
