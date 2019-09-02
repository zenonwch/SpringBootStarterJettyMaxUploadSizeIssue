package com.example.springbootstarterjettyissue;

import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;

@Configuration
public class JettyConfiguration {

    @Value("classpath:jetty-env.xml")
    private Resource jettyEnvResource;

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        final JettyServletWebServerFactory jettyContainer = new JettyServletWebServerFactory();
        jettyContainer.addServerCustomizers(jettyServerCustomizer());
        return jettyContainer;
    }

    private JettyServerCustomizer jettyServerCustomizer() {
        return server -> {
            try {
                final WebAppContext webAppContext = (WebAppContext) server.getHandler();
                new XmlConfiguration(new FileInputStream(jettyEnvResource.getFile()))
                        .configure(webAppContext);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
