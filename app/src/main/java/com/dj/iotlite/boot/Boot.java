package com.dj.iotlite.boot;


import android.app.Service;
import android.util.Log;

import com.dj.iotlite.Context;
import com.dj.iotlite.IotService;
import com.dj.iotlite.actions.ActionPayload;
import com.dj.iotlite.actions.Actions;
import com.dj.iotlite.actions.Config;
import com.dj.iotlite.actions.Exec;
import com.dj.iotlite.actions.Read;
import com.dj.iotlite.actions.Reset;
import com.dj.iotlite.actions.Update;
import com.dj.iotlite.capability.Bluetooth;
import com.dj.iotlite.capability.Coap;
import com.dj.iotlite.capability.Ethernet;
import com.dj.iotlite.capability.Modbus;
import com.dj.iotlite.capability.Mqtt;
import com.dj.iotlite.capability.Power;
import com.dj.iotlite.capability.Wifi;
import com.dj.iotlite.events.LogEvent;
import com.dj.iotlite.utils.JsonUtils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.greenrobot.eventbus.EventBus;


public class Boot {

    Service iotService;

    public MqttClient getMqttClient() {
        return mqttClient;
    }

    public void setMqttClient(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    MqttClient mqttClient = null;

    public void init(IotService iotService){
        this.iotService=iotService;
        initCapability();
        initActions();
        initMqttClient();
    }

    public void initCapability() {
        Context.capability.add(new Bluetooth());
        Context.capability.add(new Wifi());
        Context.capability.add(new Power());
        Context.capability.add(new Ethernet());
        Context.capability.add(new Mqtt());
        Context.capability.add(new Modbus());
        Context.capability.add(new Coap());
    }

    public void  initActions(){
        Context.actions.put("config",new Config());
        Context.actions.put("read",new Read());
        Context.actions.put("exec",new Exec());
        Context.actions.put("update",new Update());
        Context.actions.put("reset",new Reset());
    }

    public  void  initMqttClient(){
        try {
            mqttClient = new MqttClient(com.dj.iotlite.Config.broker, com.dj.iotlite.Config.clientId, new MemoryPersistence());
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d(this.getClass().getName(), "连接丢失: ");
                }

                @Override
                public void messageArrived(String topic, MqttMessage msg) throws Exception {
                    try{
                        ActionPayload actionPayload = JsonUtils.fromPayload(msg.getPayload());
                        Actions actions=Context.actions.get(actionPayload.getAction());
                        if (actions!=null){
                            actions.run(actionPayload);
                        }else {
                            EventBus.getDefault().post(new LogEvent(new String(msg.getPayload())));
                            Log.d(this.getClass().getName(), "无法处理的: " + new String(msg.getPayload(), "UTF-8"));
                        }
                    }catch (Exception e){
                        EventBus.getDefault().post(new LogEvent("异常"+e.getMessage()));
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d(this.getClass().getName(), "投递完成: " + token.getTopics()[0]);
                }
            });
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            mqttClient.connect(options);
//            if (mqttClient.isConnected()) {
//                Log.d(this.getClass().getName(), "订阅: " + com.dj.iotlite.Config.getDeviceTopic());
//
//
//            }
            while (mqttClient.isConnected()){
                if(mqttClient.isConnected()){
                    mqttClient.subscribe(com.dj.iotlite.Config.getDeviceTopic());
                    SignupCapability();
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (MqttException e) {
            Log.d(this.getClass().getName(), "异常: ");
            e.printStackTrace();
        }
    }

    private void SignupCapability() throws MqttException {
        EventBus.getDefault().post(new LogEvent("注册设备"));
//        String a= JsonUtils.getPayloadBytes(new ActionPayload("signup", Context.capability);
        mqttClient.publish(com.dj.iotlite.Config.getProductTopic(),"22222".getBytes(), 0, false);
    }

}
