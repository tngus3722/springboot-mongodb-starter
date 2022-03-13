package com.tngus3722.springbootmongodbstarter.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestListener implements ApplicationListener<TestEvent> {

    @Async
    @Override
    public void onApplicationEvent(TestEvent event) {
        System.out.println("onApplicationEvent" + Thread.currentThread());
        System.out.println(event.getCategoryId());
    }
}
