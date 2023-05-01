package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.InventoryItemDto;

import java.util.List;

public interface InventoryItemService {

    List<InventoryItemDto> findAllInventoryItems();

    void createInventoryItem(InventoryItemDto inventoryItemDto);

    void updateInventoryItem(InventoryItemDto inventoryItemDto);

    InventoryItemDto findInventoryItemById(Long inventoryItemId);

    void deleteInventoryItem(Long inventoryItemId);

    List<InventoryItemDto> searchInventoryItemsByName(String name);

    void increaseOneTimesRentedInventoryItem(Long inventoryItemId);
}
