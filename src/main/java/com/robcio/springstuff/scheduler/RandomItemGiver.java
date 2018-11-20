package com.robcio.springstuff.scheduler;

import com.robcio.springstuff.entity.InventoryItem;
import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.service.InventoryItemService;
import com.robcio.springstuff.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RandomItemGiver {

    private static final Logger logger = LoggerFactory.getLogger(RandomItemGiver.class);

    private final UserService userService;
    private final InventoryItemService inventoryItemService;

    @Autowired
    public RandomItemGiver(final UserService userService, final InventoryItemService inventoryItemService) {
        this.userService = userService;
        this.inventoryItemService = inventoryItemService;
    }

    @Scheduled(fixedRate = 240000)
    public void giveRandomUserRandomItem() {
        final User randomUser = userService.getRandomUser();
        final InventoryItem inventoryItem = inventoryItemService.giveUserRandomItem(randomUser.getId());
        logger.debug("Giving {} to {}", inventoryItem.getName(), randomUser.getName());
    }
}
