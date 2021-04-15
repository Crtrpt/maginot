package com.dj.iotlite.actions;

import android.util.Log;

import com.dj.iotlite.events.LogEvent;

import org.greenrobot.eventbus.EventBus;

public class Config implements Actions{
    @Override
    public void run(ActionPayload actionPayload) {
        Log.d(this.getClass().getName(), "执行更新操作");
        EventBus.getDefault().post(new LogEvent("执行更新操作"));
    }
}
