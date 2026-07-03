package com.taskfloweb.fx.byezbercime.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.support.PropertySourceFactory;

@Configuration
@PropertySources(value =
        {
                @PropertySource(value = "classpath:secretkey.yml",factory = PropertySourceFactory.class)
        })
public class JWTConfiguration {

    @Value("${jjwt-security-key}")
    private String API_KEY;

    public String JwtAPIKey() {
        return API_KEY;
    }
}
