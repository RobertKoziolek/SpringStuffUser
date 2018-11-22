package com.robcio.springstuff.controller;

import com.robcio.springstuff.dto.InventoryItemData;
import com.robcio.springstuff.entity.InventoryItem;
import com.robcio.springstuff.repository.UserRepository;
import com.robcio.springstuff.service.InventoryItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
@Api(value = "/items", description = "Operations about items")
public class InventoryItemController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryItemController.class);

    private final InventoryItemService inventoryItemService;
    private final UserRepository userRepository;

    @Autowired
    public InventoryItemController(final InventoryItemService inventoryItemService,
                                   final UserRepository userRepository) {
        this.inventoryItemService = inventoryItemService;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/add")
    @ApiOperation(value = "Adds an item")
    public Long addItem(@RequestBody final InventoryItemData itemData) {
        logger.debug("Adding {} item to user with id {}", itemData.getName(), itemData.getUserId());
        final InventoryItem item = inventoryItemService.createItem(itemData);
        return item.getId();
    }

    @GetMapping(path = "/{userId}")
    @ApiOperation(value = "Gets items a user possesses")
    public String getForUser(@PathVariable final Long userId) {
        logger.debug("Returning user with id {}", userId);
        return inventoryItemService.getUserItemsListed(userId);
    }
}
