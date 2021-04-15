package com.dj.iotlite;

import com.dj.iotlite.actions.Actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Context {
    HashMap<String,Object> property=new HashMap<>();
    static  public HashSet<CapabilityInterface> capability= new HashSet<>();
    static  public HashMap<String,Actions> actions= new HashMap<>();
}
