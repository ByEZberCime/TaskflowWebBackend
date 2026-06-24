package com.taskfloweb.fx.byezbercime.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.support.PropertySourceFactory;
import javax.sql.DataSource;

@Configuration

@PropertySources(value = {
        @PropertySource(value = "classpath:config.yml",factory = PropertySourceFactory.class)
})

public class WebConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}
