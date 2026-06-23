package com.taskfloweb.fx.byezbercime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.taskfloweb.fx.byezbercime")
@EnableJpaRepositories(basePackages = "com.taskfloweb.fx.byezbercime")
@SpringBootApplication
public class TaskflowWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskflowWebApplication.class, args);
	}

}
