package com.tngus3722.springbootmongodbstarter.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TestEvent extends ApplicationEvent {

    private String categoryId;

    public TestEvent(Object source, String categoryId) {
        super(source);
        this.categoryId = categoryId;
    }
}
