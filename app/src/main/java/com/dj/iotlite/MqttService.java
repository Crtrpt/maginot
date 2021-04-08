package com.dj.iotlite;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.util.Xml;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

public class MqttService extends Service {

    MqttClient mqttClient = null;

    public MqttService() {
    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    String topic = "/android/" + DataGate.propertys.get("android_id");
                    Log.d(this.getClass().getName(), "topic" + topic);
                    mqttClient.publish(topic, DataGate.getProperty(), 0, false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }, 5000, 10000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(this.getClass().getName(), "Service 线程" + Thread.currentThread().getId());


        try {
            mqttClient = new MqttClient("tcp://broker.emqx.io:1883", "androidMqtt", new MemoryPersistence());

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d(this.getClass().getName(), "连接丢失: ");
                }

                @Override
                public void messageArrived(String topic, MqttMessage msg) throws Exception {
                    Log.d(this.getClass().getName(), "收到消息: " + new String(msg.getPayload(), "UTF-8"));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d(this.getClass().getName(), "投递完成: ");
                }
            });
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            IMqttToken mq = mqttClient.connectWithResult(options);
            mqttClient.subscribe("/test/android/");

            startTimer();

        } catch (MqttException e) {
            Log.d(this.getClass().getName(), "异常: ");
            e.printStackTrace();
        }

        return null;
    }


}
