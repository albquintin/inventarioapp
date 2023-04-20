package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.InventoryItemDto;

import java.util.List;

public interface InventoryItemService {

    List<InventoryItemDto> findAllInventoryItems();

    void createInventoryItem(InventoryItemDto inventoryItemDto);


}
