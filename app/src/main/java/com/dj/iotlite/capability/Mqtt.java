package com.dj.iotlite.capability;

import com.dj.iotlite.utils.NetworkUtils;
import com.dj.iotlite.annotation.Capability;
import com.dj.iotlite.capability.mqtt.Authenticator;
import com.dj.iotlite.capability.mqtt.PublisherListener;
import com.dj.iotlite.capability.mqtt.SslContextCreator;
import com.dj.iotlite.events.LogEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import io.moquette.interception.InterceptHandler;
import io.moquette.server.Server;
import io.moquette.server.config.IConfig;
import io.moquette.server.config.MemoryConfig;
import io.moquette.spi.impl.subscriptions.Topic;
import io.moquette.spi.security.IAuthorizator;

@Capability(value="mqtt")
public class Mqtt implements CapabilityInterface {
    IConfig config =new MemoryConfig(new Properties());
    public Mqtt(){
        EventBus.getDefault().post(new LogEvent("启动mqtt服务器"+ NetworkUtils.getLocalIPAddress()));
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
    public void run() throws IOException {
        List<? extends InterceptHandler> userHandlers = Collections.singletonList(new PublisherListener());

        mqttBroker.startServer(config, userHandlers, new SslContextCreator(), new Authenticator(), new IAuthorizator() {
            @Override
            public boolean canWrite(Topic topic, String user, String client) {
                return false;
            }

            @Override
            public boolean canRead(Topic topic, String user, String client) {
                return false;
            }
        });
    }

    @Override
    public void setAvailable(boolean isAvailable) {

    }
}
