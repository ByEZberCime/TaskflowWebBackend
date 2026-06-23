package com.taskfloweb.fx.byezbercime.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.Getter;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebOpenAPIConfiguration {

    @Value("${application-version}")
    @Getter
    private String apiVersion;

    @Bean
    public GroupedOpenApi taskFlowAPIGroup() {
        return GroupedOpenApi
                .builder()
                .group("TaskFlow docs")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI taskFlowAPI() {
        return new OpenAPI()
                .info(new Info()
                        .version(apiVersion)
                        .description("TaskFlow API Documentation")
                        .title("TaskFlow API"));
    }

}
