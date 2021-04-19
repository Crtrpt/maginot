package com.dj.iotlite.capability.mqtt;

import io.moquette.spi.security.IAuthenticator;

public class Authenticator implements IAuthenticator {
    @Override
    public boolean checkValid(String clientId, String username, byte[] password) {
        return false;
    }
}
