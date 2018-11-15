package com.robcio.springstuff.controller.request;

import com.robcio.springstuff.enumeration.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryItemData {

    private Long userId;

    private String name;

    private ItemType type;
}
