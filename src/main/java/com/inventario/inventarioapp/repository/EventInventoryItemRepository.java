package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.EventInventoryItem;
import com.inventario.inventarioapp.entity.EventInventoryItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventInventoryItemRepository extends JpaRepository<EventInventoryItem, EventInventoryItemId> {

    @Query(value = "select * from events_inventory_items where event_id = :eventId", nativeQuery = true)
    List<EventInventoryItem> findByEvent(Long eventId);
}
