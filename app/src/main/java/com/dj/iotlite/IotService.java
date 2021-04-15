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

    private void startTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    String topic = Config.getDeviceTopic();
                    Log.d(this.getClass().getName(), "topic" + topic);
                    mqttClient.publish(Config.getDeviceTopic(), DataGate.getProperty(), 0, false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }, 5000, 10000);
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
