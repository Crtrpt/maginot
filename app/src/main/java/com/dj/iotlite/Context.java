package com.dj.iotlite;

import com.dj.iotlite.actions.Actions;
import com.dj.iotlite.capability.CapabilityInterface;

import java.util.HashMap;
import java.util.HashSet;

public class Context {
    HashMap<String,Object> property=new HashMap<>();
    static  public HashSet<CapabilityInterface> capability= new HashSet<>();
    static  public HashMap<String,Actions> actions= new HashMap<>();
}
