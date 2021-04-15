package com.dj.iotlite.actions;

import android.util.Log;

import com.dj.iotlite.events.LogEvent;

import org.greenrobot.eventbus.EventBus;

public class Reset implements Actions{
    @Override
    public void run(ActionPayload actionPayload) {
        Log.d(this.getClass().getName(), "执行重置操作");
        EventBus.getDefault().post(new LogEvent("执行重置操作"));
    }
}
