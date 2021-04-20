package com.mercadolibre.IpAnalyzer.services;

import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
public class IpAddressServiceImpl implements IpAddressService{

    @Override
    public String getServerAddress() throws Exception {
        final String serverAddress = InetAddress.getLocalHost().getHostAddress();

        return serverAddress;
    }
}
