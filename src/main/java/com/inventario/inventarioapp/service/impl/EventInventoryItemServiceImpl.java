package com.inventario.inventarioapp.service.impl;

import com.inventario.inventarioapp.dto.EventInventoryItemDto;
import com.inventario.inventarioapp.entity.Event;
import com.inventario.inventarioapp.entity.EventInventoryItem;
import com.inventario.inventarioapp.entity.InventoryItem;
import com.inventario.inventarioapp.mapper.EventInventoryItemMapper;
import com.inventario.inventarioapp.repository.EventInventoryItemRepository;
import com.inventario.inventarioapp.repository.EventRepository;
import com.inventario.inventarioapp.repository.InventoryItemRepository;
import com.inventario.inventarioapp.service.EventInventoryItemService;
import org.springframework.stereotype.Service;

@Service
public class EventInventoryItemServiceImpl implements EventInventoryItemService {

    EventInventoryItemRepository eventInventoryItemRepository;
    InventoryItemRepository inventoryItemRepository;
    EventRepository eventRepository;

    public EventInventoryItemServiceImpl(EventInventoryItemRepository eventInventoryItemRepository, InventoryItemRepository inventoryItemRepository, EventRepository eventRepository) {
        this.eventInventoryItemRepository = eventInventoryItemRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void rentInventoryItem(EventInventoryItemDto eventInventoryItemDto) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(eventInventoryItemDto.getInventoryItemId()).get();
        Event event = eventRepository.findById(eventInventoryItemDto.getEventId()).get();
        EventInventoryItem eventInventoryItem = EventInventoryItemMapper.mapToEventInventoryItem(eventInventoryItemDto, inventoryItem, event);
        eventInventoryItemRepository.save(eventInventoryItem);
    }
}
