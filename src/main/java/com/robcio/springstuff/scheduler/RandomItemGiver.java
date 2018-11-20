package com.robcio.springstuff.scheduler;

import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.event.RandomRewardEvent;
import com.robcio.springstuff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RandomItemGiver {

    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public RandomItemGiver(final UserService userService, final ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Scheduled(fixedRate = 240000)
    public void giveRandomUserRandomItem() {
        final User randomUser = userService.getRandomUser();
        final RandomRewardEvent randomRewardEvent = new RandomRewardEvent(this,
                                                                          randomUser.getId(),
                                                                          randomUser.getName());
        applicationEventPublisher.publishEvent(randomRewardEvent);
    }
}
