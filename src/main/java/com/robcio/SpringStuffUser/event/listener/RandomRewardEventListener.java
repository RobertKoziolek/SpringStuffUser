package com.robcio.SpringStuffUser.event.listener;

import com.robcio.SpringStuffUser.entity.InventoryItem;
import com.robcio.SpringStuffUser.event.RandomRewardEvent;
import com.robcio.SpringStuffUser.service.InventoryItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RandomRewardEventListener {

    private static final Logger logger = LoggerFactory.getLogger(RandomRewardEventListener.class);

    private final InventoryItemService inventoryItemService;

    @Autowired
    public RandomRewardEventListener(final InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @EventListener
    public void handleEvent(final RandomRewardEvent event) {
        final InventoryItem item = inventoryItemService.getRandomItem();
        inventoryItemService.giveItem(event.getUserId(), item);
        logger.debug("Giving {} to {}", item.getName(), event.getName());
    }
}
