package com.robcio.SpringStuffUser.dto;

import com.robcio.SpringStuffUser.enumeration.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryItemData {

    private String name;

    private ItemType type;
}
