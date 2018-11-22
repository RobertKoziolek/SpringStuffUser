package com.robcio.springstuff.controller.item;

import com.robcio.springstuff.controller.UserItemRestController;
import com.robcio.springstuff.service.InventoryItemService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@UserItemRestController
public class GetItemsController {

    private static final Logger logger = LoggerFactory.getLogger(GetItemsController.class);

    private final InventoryItemService inventoryItemService;

    @Autowired
    public GetItemsController(final InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @GetMapping(path = "/")
    @ApiOperation(value = "Gets items a user possesses")
    public String getForUser(@PathVariable final Long userId) {
        logger.debug("Returning user with id {}", userId);
        return inventoryItemService.getUserItemsListed(userId);
    }
}
