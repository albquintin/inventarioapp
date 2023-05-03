package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.EventInventoryItemDto;
import com.inventario.inventarioapp.entity.EventInventoryItemId;

import java.util.List;

public interface EventInventoryItemService {

    void rentInventoryItem(EventInventoryItemDto eventInventoryItemDto);
    List<EventInventoryItemDto> findByEvent(Long eventId);

    void deleteEventInventoryItemById(EventInventoryItemId eventInventoryItemId);
}
