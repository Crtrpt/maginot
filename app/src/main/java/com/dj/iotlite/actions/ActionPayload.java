package com.dj.iotlite.actions;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.io.IOException;

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



}
