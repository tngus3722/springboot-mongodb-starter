package com.tngus3722.springbootmongodbstarter.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestPublisher {

    private final ApplicationEventPublisher publisher;

    public void publishEvent(String categoryId) {
        System.out.println("publishEvent" + Thread.currentThread());
        publisher.publishEvent(new TestEvent(this, categoryId));
    }
}
