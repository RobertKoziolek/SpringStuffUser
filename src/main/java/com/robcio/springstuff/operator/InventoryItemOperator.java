package com.robcio.springstuff.operator;

import com.robcio.springstuff.controller.request.InventoryItemData;
import com.robcio.springstuff.entity.InventoryItem;
import com.robcio.springstuff.repository.InventoryItemRepository;
import com.robcio.springstuff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemOperator {

    private final InventoryItemRepository inventoryItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public InventoryItemOperator(final InventoryItemRepository inventoryItemRepository,
                                 final UserRepository userRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.userRepository = userRepository;
    }

    public String getUserItemsListed(final Long userId) {
        return inventoryItemRepository.findByUserId(userId)
                                      .stream()
                                      .map(InventoryItem::getName)
                                      .reduce((f, s) -> String.format("%s, %s", f, s))
                                      .orElse("");
    }

    public void createItem(final InventoryItemData itemData) {
        final InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setName(itemData.getName());
        inventoryItem.setType(itemData.getType());
        inventoryItem.setUser(userRepository.findById(itemData.getUserId())
                                            .orElseThrow(() -> new RuntimeException("Cannot find the user")));
        inventoryItemRepository.save(inventoryItem);
    }
}
