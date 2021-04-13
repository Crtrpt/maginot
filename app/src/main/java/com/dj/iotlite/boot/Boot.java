package com.dj.iotlite.boot;



import android.os.Build;

import androidx.annotation.RequiresApi;


import com.dj.iotlite.CapabilityInterface;
import com.dj.iotlite.CapabilityWarp;
import com.dj.iotlite.Context;
import com.dj.iotlite.capability.BluetoothCapabilityInterfaceImpl;
import com.dj.iotlite.capability.WifiCapabilityInterfaceImpl;


public class Boot {
    static public void getClassByAnnotation() {

        CapabilityWarp warp=new CapabilityWarp();
        warp.setInstance(new BluetoothCapabilityInterfaceImpl());
        warp.setClazz(BluetoothCapabilityInterfaceImpl.class);
        Context.capability.put(warp.getInstance().getName(),warp);


        CapabilityWarp wifi=new CapabilityWarp();
        wifi.setInstance(new WifiCapabilityInterfaceImpl());
        wifi.setClazz(WifiCapabilityInterfaceImpl.class);
        Context.capability.put(wifi.getInstance().getName(),wifi);
    }
}
