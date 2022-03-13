package com.tngus3722.springbootmongodbstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringbootMongodbStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbStarterApplication.class, args);
	}

}
