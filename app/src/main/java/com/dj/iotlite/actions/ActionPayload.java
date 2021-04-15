package com.dj.iotlite.actions;

import android.util.Log;

import com.google.gson.Gson;

public class ActionPayload {


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    String action="";

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    Object payload=new Object();
    public ActionPayload(){

    }

    public ActionPayload(String action, Object payload){
        this.action=action;
        this.payload=payload;
    }

    public ActionPayload  fromPayload(byte[] payload){
        Gson gson=new Gson();
        return gson.fromJson(new String(payload),this.getClass());
    }

    public byte[] getPayloadBytes(){
        Gson gson=new Gson();
        String data=gson.toJson(this);
        Log.d(this.getClass().getName(), "注册能力:"+data);
        return data.getBytes();
    }

}
