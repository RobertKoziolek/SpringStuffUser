package com.robcio.springstuff.util;

import com.robcio.springstuff.enumeration.ItemType;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ItemNameGenerator {

    private final Random random = new Random();
    private final List<String> firstPartList;
    private final Map<ItemType, List<String>> secondPartMap;

    public ItemNameGenerator() {
        this.firstPartList = new LinkedList<>(
                Arrays.asList("Magic", "Adamant", "Metal", "Leather", "Bone", "Wooden", "Heavenly", "Hellish", "Rusty",
                              "Broken", "Damaged", "Fine", "Excellent", "Steel", "Demon", "Silver", "Gold", "Paper"));
        this.secondPartMap = new HashMap<>();
        this.secondPartMap.put(ItemType.WEAPON, Arrays.asList("Sword", "Knife", "Cudgel", "Wand", "Bow", "Handgun"));
        this.secondPartMap.put(ItemType.ARMOR, Arrays.asList("Breastplate", "Jacket", "Shirt"));
        this.secondPartMap.put(ItemType.HELMET, Arrays.asList("Cap", "Hat", "Helmet"));
        this.secondPartMap.put(ItemType.CONTAINER, Arrays.asList("Bag", "Box", "Backpack"));
        this.secondPartMap.put(ItemType.KEY, Arrays.asList("Key", "Card", "Id"));
    }

    public String getFor(final ItemType type) {
        final String firstPart = firstPartList.get(random.nextInt(firstPartList.size()));
        final List<String> secondPartList = secondPartMap.get(type);
        final String secondPart = secondPartList.get(random.nextInt(secondPartList.size()));
        return String.format("%s %s", firstPart, secondPart);
    }
}
