package com.dj.iotlite.utils;

import android.util.Log;

import com.dj.iotlite.actions.ActionPayload;
import com.google.gson.Gson;

public class JsonUtils {

    public static ActionPayload fromPayload(byte[] payload){
        Gson gson=new Gson();
        return gson.fromJson(new String(payload),ActionPayload.class);
    }


    public static byte[] getPayloadBytes(Object obj){
        Gson gson=new Gson();
        String data=gson.toJson(obj);
        Log.d(obj.getClass().getName(), "注册能力:"+data);
        return data.getBytes();
    }
}
