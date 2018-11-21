package com.robcio.springstuff.service;

import com.robcio.springstuff.dto.InventoryItemData;
import com.robcio.springstuff.entity.InventoryItem;
import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.enumeration.ItemType;
import com.robcio.springstuff.repository.InventoryItemRepository;
import com.robcio.springstuff.util.ItemNameGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class InventoryItemServiceTest {

    @Mock
    private final ItemNameGenerator itemNameGenerator = new ItemNameGenerator();
    @Mock
    private InventoryItemRepository inventoryItemRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private InventoryItemService inventoryItemService;

    @Test
    public void randomItem() {
        //given

        //when
        when(itemNameGenerator.getFor(any(ItemType.class))).thenReturn("Test item");
        final InventoryItem randomItem = inventoryItemService.getRandomItem();

        //then
        assertThat(randomItem).isNotNull();
        assertThat(randomItem.getName()).isEqualTo("Test item");
        assertThat(randomItem.getType()).isNotNull()
                                        .isInstanceOf(ItemType.class);
        assertThat(randomItem.getUser()).isNull();
    }

    @Test
    public void randomItemWithType() {
        //given

        //when
        when(itemNameGenerator.getFor(any(ItemType.class))).thenReturn("Test item");
        final InventoryItem randomItem = inventoryItemService.getRandomItem(ItemType.ARMOR);

        //then
        assertThat(randomItem).isNotNull();
        assertThat(randomItem.getName()).isEqualTo("Test item");
        assertThat(randomItem.getType()).isEqualTo(ItemType.ARMOR);
        assertThat(randomItem.getUser()).isNull();
    }

    @Test
    public void createItem() {
        //given
        final String itemName = "Test item";
        final ItemType itemType = ItemType.ARMOR;
        final long userId = 9L;
        final InventoryItemData itemData = new InventoryItemData();
        itemData.setName(itemName);
        itemData.setType(itemType);
        itemData.setUserId(userId);
        final User user = new User();
        user.setId(userId);

        //when
        when(userService.getUser(userId)).thenReturn(user);
        final InventoryItem item = inventoryItemService.createItem(itemData);

        //then
        assertThat(item).isNotNull();
        assertThat(item.getUser()).isNotNull();
        assertThat(item.getUser()
                       .getId()).isEqualTo(userId);
        assertThat(item.getType()).isEqualTo(itemType);
        assertThat(item.getName()).isEqualTo(itemName);
    }

    @Test
    public void giveItem() {
        //given
        final long userId = 9L;
        final User user = new User();
        user.setId(userId);

        final InventoryItem item = new InventoryItem();
        item.setName("Test item");
        item.setType(ItemType.ARMOR);

        //when
        when(userService.getUser(userId)).thenReturn(user);
        inventoryItemService.giveItem(userId, item);

        //then
        verify(inventoryItemRepository, times(1)).save(item);
        assertThat(item.getUser()).isNotNull();
        assertThat(item.getUser()
                       .getId()).isEqualTo(userId);
    }

    @Test
    public void getUserItemsListed() {
        //given
        final InventoryItem item = new InventoryItem();
        item.setName("An item");
        final InventoryItem item2 = new InventoryItem();
        item2.setName("Test item");

        final long userId = 9L;
        final User user = new User();
        user.setId(userId);
        user.setItems(new HashSet<>(Arrays.asList(item, item2)));

        //when
        when(inventoryItemRepository.findByUserId(userId)).thenReturn(Arrays.asList(item, item2));
        final String userItemsListed = inventoryItemService.getUserItemsListed(userId);

        //then
        assertThat(userItemsListed).isEqualTo("An item, Test item");
    }
}