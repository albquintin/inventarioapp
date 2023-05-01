package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.EventInventoryItemDto;
import com.inventario.inventarioapp.entity.EventInventoryItem;

public interface EventInventoryItemService {

    void rentInventoryItem(EventInventoryItemDto eventInventoryItemDto);
}
