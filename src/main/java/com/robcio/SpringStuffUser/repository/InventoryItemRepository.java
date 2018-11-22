package com.robcio.SpringStuffUser.repository;

import com.robcio.SpringStuffUser.entity.InventoryItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends CrudRepository<InventoryItem, Long> {

    List<InventoryItem> findByUserId(final Long userId);
}
