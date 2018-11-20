package com.robcio.springstuff.service;

import com.robcio.springstuff.dto.InventoryItemData;
import com.robcio.springstuff.entity.InventoryItem;
import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.enumeration.ItemType;
import com.robcio.springstuff.repository.InventoryItemRepository;
import com.robcio.springstuff.repository.UserRepository;
import com.robcio.springstuff.util.ItemNameGenerator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final UserRepository userRepository;
    private final ItemNameGenerator itemNameGenerator;

    @Autowired
    public InventoryItemService(final InventoryItemRepository inventoryItemRepository,
                                final UserRepository userRepository,
                                final ItemNameGenerator itemNameGenerator) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.userRepository = userRepository;
        this.itemNameGenerator = itemNameGenerator;
    }

    public InventoryItem giveUserRandomItem(final Long userId) {
        final ItemType randomType = ItemType.values()[new Random().nextInt(ItemType.values().length)];
        final InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setUser(getUser(userId));
        inventoryItem.setType(randomType);
        inventoryItem.setName(itemNameGenerator.getFor(randomType));
        inventoryItemRepository.save(inventoryItem);
        return inventoryItem;
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
        inventoryItem.setUser(getUser(itemData.getUserId()));
        inventoryItemRepository.save(inventoryItem);
    }

    @NotNull
    private User getUser(final Long userId) {
        return userRepository.findById(userId)
                             .orElseThrow(() -> new RuntimeException("Cannot find the user"));
    }
}
