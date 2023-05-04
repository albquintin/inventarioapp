package com.inventario.inventarioapp.mapper;

import com.inventario.inventarioapp.dto.EventInventoryItemDto;
import com.inventario.inventarioapp.entity.Event;
import com.inventario.inventarioapp.entity.EventInventoryItem;
import com.inventario.inventarioapp.entity.EventInventoryItemId;
import com.inventario.inventarioapp.entity.InventoryItem;

public class EventInventoryItemMapper {

    public static EventInventoryItemDto mapToEventInventoryItemDto(EventInventoryItem eventInventoryItem){
        return EventInventoryItemDto.builder()
                .inventoryItemId(eventInventoryItem.getInventoryItem().getId())
                .inventoryItemName(eventInventoryItem.getInventoryItem().getName())
                .eventId(eventInventoryItem.getEvent().getId())
                .eventName(eventInventoryItem.getEvent().getName())
                .inventoryItemPosition(eventInventoryItem.getInventoryItem().getPosition())
                .clientName(eventInventoryItem.getEvent().getClient().getName())
                .eventDay(eventInventoryItem.getEvent().getEventDay())
                .amount(eventInventoryItem.getAmount())
                .totalPrice(eventInventoryItem.getAmount()*eventInventoryItem.getInventoryItem().getPrice())
                .build();
    }

    public static EventInventoryItem mapToEventInventoryItem(EventInventoryItemDto eventInventoryItemDto,
                                                             InventoryItem inventoryItem, Event event){
        EventInventoryItemId eventInventoryItemId = new EventInventoryItemId(eventInventoryItemDto.getEventId(), eventInventoryItemDto.getInventoryItemId());
        return EventInventoryItem.builder()
                .id(eventInventoryItemId)
                .inventoryItem(inventoryItem)
                .event(event)
                .amount(eventInventoryItemDto.getAmount())
                .build();
    }
}
