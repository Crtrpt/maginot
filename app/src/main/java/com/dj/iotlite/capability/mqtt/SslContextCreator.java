package com.dj.iotlite.capability.mqtt;

import javax.net.ssl.SSLContext;

import io.moquette.spi.security.ISslContextCreator;

public class SslContextCreator  implements ISslContextCreator {
    @Override
    public SSLContext initSSLContext() {
        return null;
    }
}
