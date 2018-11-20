package com.robcio.springstuff.controller;

import com.robcio.springstuff.dto.InventoryItemData;
import com.robcio.springstuff.enumeration.ItemType;
import com.robcio.springstuff.repository.UserRepository;
import com.robcio.springstuff.service.InventoryItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/items")
@Api(value = "/items", description = "Operations about items")
public class InventoryItemController {

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
    public String addItem(@ModelAttribute final InventoryItemData itemData) {
        inventoryItemService.createItem(itemData);
        return "redirect:/items/" + itemData.getUserId();
    }

    @GetMapping(path = "/add/view")
    @ApiOperation(value = "Shows 'add item' thymeleaf page")
    public String addItemView(final Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("itemTypes", ItemType.values());
        return "add_item";
    }

    @ResponseBody
    @GetMapping(path = "/{userId}")
    @ApiOperation(value = "Gets items a user possesses")
    public String getForUser(@PathVariable final Long userId) {
        return inventoryItemService.getUserItemsListed(userId);
    }
}
