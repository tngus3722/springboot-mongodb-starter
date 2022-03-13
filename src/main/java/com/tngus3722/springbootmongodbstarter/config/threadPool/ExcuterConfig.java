package com.tngus3722.springbootmongodbstarter.config.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcuterConfig {

    @Bean
    public ExecutorService customThreadPool(){
        return Executors.newFixedThreadPool(10);
    }

}
