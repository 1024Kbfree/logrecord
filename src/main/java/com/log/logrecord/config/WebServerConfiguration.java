package com.log.logrecord.config;

import org.apache.catalina.LifecycleListener;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.coyote.ProtocolHandler;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServerConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        TomcatServletWebServerFactory webServerFactory = (TomcatServletWebServerFactory) factory;
        webServerFactory.setProtocol("org.apache.coyote.http11.Http11AprProtocol");

        AprLifecycleListener aprLifecycleListener = new AprLifecycleListener();
        aprLifecycleListener.setSSLEngine("off");
        aprLifecycleListener.setUseOpenSSL(false);
        webServerFactory.addContextLifecycleListeners(aprLifecycleListener);


    }
}
