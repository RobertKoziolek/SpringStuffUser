package com.robcio.springstuff.dto;

import com.robcio.springstuff.enumeration.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryItemData {

    private String name;

    private ItemType type;
}
