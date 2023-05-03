package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.InventoryItemDto;

import java.util.List;
import java.util.Optional;

public interface InventoryItemService {

    List<InventoryItemDto> findActiveInventoryItems();
    List<InventoryItemDto> findOldInventoryItems();

    void createInventoryItem(InventoryItemDto inventoryItemDto);

    void updateInventoryItem(InventoryItemDto inventoryItemDto);

    InventoryItemDto findInventoryItemById(Long inventoryItemId);

    void logicDeleteInventoryItem(Long inventoryItemId);
    void deleteInventoryItem(Long inventoryItemId);
    void restoreInventoryItem(Long inventoryItemId);

    void increaseOneTimesRentedInventoryItem(Long inventoryItemId);

    Optional<InventoryItemDto> findInventoryItemByPosition(String position);
}
