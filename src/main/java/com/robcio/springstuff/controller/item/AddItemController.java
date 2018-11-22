package com.robcio.springstuff.controller.item;

import com.robcio.springstuff.controller.UserItemRestController;
import com.robcio.springstuff.dto.InventoryItemData;
import com.robcio.springstuff.entity.InventoryItem;
import com.robcio.springstuff.service.InventoryItemService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@UserItemRestController
public class AddItemController {

    private static final Logger logger = LoggerFactory.getLogger(AddItemController.class);

    private final InventoryItemService inventoryItemService;

    @Autowired
    public AddItemController(final InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @PostMapping(path = "/add")
    @ApiOperation(value = "Adds an item")
    public Long addItem(@PathVariable final Long userId, @RequestBody final InventoryItemData itemData) {
        logger.debug("Adding {} item to user with id {}", itemData.getName(), userId);
        final InventoryItem item = inventoryItemService.createItem(itemData);
        inventoryItemService.giveItem(userId, item);
        return item.getId();
    }
}
