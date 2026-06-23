package com.taskfloweb.fx.byezbercime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages = "com.taskfloweb.fx.byezbercime")
@EnableJpaRepositories(basePackages = "com.taskfloweb.fx.byezbercime")
@EnableWebSecurity
@EnableMethodSecurity
@SpringBootApplication
public class TaskflowWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskflowWebApplication.class, args);
	}

}
