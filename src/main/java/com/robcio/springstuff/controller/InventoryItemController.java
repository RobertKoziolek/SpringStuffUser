package com.robcio.springstuff.controller;

import com.robcio.springstuff.controller.request.InventoryItemData;
import com.robcio.springstuff.enumeration.ItemType;
import com.robcio.springstuff.operator.InventoryItemOperator;
import com.robcio.springstuff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/items")
public class InventoryItemController {

    private final InventoryItemOperator inventoryItemOperator;
    private final UserRepository userRepository;

    @Autowired
    public InventoryItemController(final InventoryItemOperator inventoryItemOperator,
                                   final UserRepository userRepository) {
        this.inventoryItemOperator = inventoryItemOperator;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/add")
    public String addItemToUser(@ModelAttribute final InventoryItemData itemData) {
        inventoryItemOperator.createItem(itemData);
        return "redirect:/items/" + itemData.getUserId();
    }

    @GetMapping(path = "/add")
    public String addItem(final Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("itemTypes", ItemType.values());
        return "add_item";
    }

    @ResponseBody
    @GetMapping(path = "/{userId}")
    public String getForUser(@PathVariable final Long userId) {
        return inventoryItemOperator.getUserItemsListed(userId);
    }
}
