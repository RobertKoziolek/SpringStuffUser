package com.robcio.springstuff.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RandomRewardEvent extends ApplicationEvent {

    private final Long userId;
    private final String name;

    public RandomRewardEvent(final Object source, final Long userId, final String name) {
        super(source);
        this.userId = userId;
        this.name = name;
    }
}
