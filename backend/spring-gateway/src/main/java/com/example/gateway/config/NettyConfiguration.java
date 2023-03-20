package com.example.gateway.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class NettyConfiguration
        implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

    @Override
    public void customize(NettyReactiveWebServerFactory webServerFactory) {
        Ssl ssl = new Ssl();
        ssl.setKeyStore("classpath:tls.jks");
        ssl.setKeyStorePassword("secret");
        ssl.setKeyPassword("password");

        webServerFactory.setPort(8080);
        webServerFactory.setSsl(ssl);
    }
}
