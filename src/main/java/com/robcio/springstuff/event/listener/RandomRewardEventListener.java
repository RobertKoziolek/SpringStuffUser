package com.robcio.springstuff.event.listener;

import com.robcio.springstuff.entity.InventoryItem;
import com.robcio.springstuff.event.RandomRewardEvent;
import com.robcio.springstuff.service.InventoryItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RandomRewardEventListener implements ApplicationListener<RandomRewardEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RandomRewardEventListener.class);

    private final InventoryItemService inventoryItemService;

    @Autowired
    public RandomRewardEventListener(final InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @Override
    public void onApplicationEvent(final RandomRewardEvent event) {
        final InventoryItem item = inventoryItemService.getRandomItem();
        inventoryItemService.giveItem(event.getUserId(), item);
        logger.debug("Giving {} to {}", item.getName(), event.getName());
    }
}
