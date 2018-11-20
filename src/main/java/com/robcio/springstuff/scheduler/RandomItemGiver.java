package com.robcio.springstuff.scheduler;

import com.robcio.springstuff.entity.InventoryItem;
import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.operator.InventoryItemOperator;
import com.robcio.springstuff.operator.UserOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RandomItemGiver {

    private static final Logger logger = LoggerFactory.getLogger(RandomItemGiver.class);
    
    private final UserOperator userOperator;
    private final InventoryItemOperator inventoryItemOperator;

    public RandomItemGiver(final UserOperator userOperator, final InventoryItemOperator inventoryItemOperator) {
        this.userOperator = userOperator;
        this.inventoryItemOperator = inventoryItemOperator;
    }

    @Scheduled(fixedRate = 120000)
    public void giveRandomUserRandomItem() {
        final User randomUser = userOperator.getRandomUser();
        final InventoryItem inventoryItem = inventoryItemOperator.giveUserRandomItem(randomUser.getId());
        logger.debug("Giving {} to {}", inventoryItem.getName(), randomUser.getName());
    }
}
