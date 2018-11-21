package com.robcio.springstuff.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RandomRewardEvent {

    private final Long userId;
    private final String name;
}
