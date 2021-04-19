package com.dj.iotlite.capability.mqtt;

import net.crtrpt.mqtt.broker.ISslContextCreator;

import io.netty.handler.ssl.SslContext;


public class SslContextCreator  implements ISslContextCreator {
    @Override
    public SslContext initSSLContext() {
        return null;
    }
}
