package com.dj.iotlite.capability;

import com.dj.iotlite.utils.NetworkUtils;
import com.dj.iotlite.annotation.Capability;
import com.dj.iotlite.capability.mqtt.Authenticator;
import com.dj.iotlite.capability.mqtt.PublisherListener;
import com.dj.iotlite.capability.mqtt.SslContextCreator;
import com.dj.iotlite.events.LogEvent;

import net.crtrpt.mqtt.broker.Server;
import net.crtrpt.mqtt.broker.config.IConfig;
import net.crtrpt.mqtt.broker.config.MemoryConfig;
import net.crtrpt.mqtt.broker.security.IAuthorizatorPolicy;
import net.crtrpt.mqtt.broker.subscriptions.Topic;
import net.crtrpt.mqtt.interception.InterceptHandler;

import org.greenrobot.eventbus.EventBus;


import java.util.Collections;
import java.util.List;
import java.util.Properties;


@Capability(value="mqtt")
public class Mqtt implements CapabilityInterface {
    IConfig config =new MemoryConfig(new Properties());

    public Mqtt(){

        this.run();
    }

    private final Server mqttBroker = new Server();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    String name;

    @Override
    public void run()  {
        List<? extends InterceptHandler> userHandlers = Collections.singletonList(new PublisherListener());
        EventBus.getDefault().post(new LogEvent("开始启动mqtt服务器"+ NetworkUtils.getLocalIPAddress()));
        mqttBroker.startServer(config, userHandlers, new SslContextCreator(), new Authenticator(), new IAuthorizatorPolicy() {
            @Override
            public boolean canWrite(Topic topic, String s, String s1) {
                return true;
            }

            @Override
            public boolean canRead(Topic topic, String s, String s1) {
                return true;
            }
        });
    }

    @Override
    public void setAvailable(boolean isAvailable) {

    }
}
