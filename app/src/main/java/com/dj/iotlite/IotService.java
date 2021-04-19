package com.dj.iotlite;

import android.app.Service;
import android.content.Intent;

import android.os.IBinder;
import android.util.Log;

import com.dj.iotlite.boot.Boot;
import com.dj.iotlite.events.LogEvent;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

public class IotService extends Service {

    MqttClient mqttClient = null;

    public IotService() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        Boot boot = new Boot();
        boot.init(this);

        EventBus.getDefault().post(new LogEvent("后台启动服务"));
        Log.d(this.getClass().getName(), "Service 线程" + Thread.currentThread().getId());
        return null;
    }




}
