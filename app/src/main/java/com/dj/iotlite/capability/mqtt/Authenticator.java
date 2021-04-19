package com.dj.iotlite.capability.mqtt;


import net.crtrpt.mqtt.broker.security.IAuthenticator;

public class Authenticator implements IAuthenticator {
    @Override
    public boolean checkValid(String clientId, String username, byte[] password) {
        return true;
    }
}
