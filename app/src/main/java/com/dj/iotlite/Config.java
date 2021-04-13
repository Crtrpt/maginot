package com.dj.iotlite;

public class Config {
    final public static String broker="tcp://broker.emqx.io:1883";
    final public static String port="";
    public static  String clientId="";
    public static  String getTopic(){
        return  "default/android/"+clientId;
    }
}
