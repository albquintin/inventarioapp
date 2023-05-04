package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.EventInventoryItem;
import com.inventario.inventarioapp.entity.EventInventoryItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventInventoryItemRepository extends JpaRepository<EventInventoryItem, EventInventoryItemId> {

    @Query(value = "select * from events_inventory_items where event_id = :eventId", nativeQuery = true)
    List<EventInventoryItem> findByEvent(Long eventId);
    @Query(value = "select ei.* from events_inventory_items ei inner join events e on ei.event_id = e.id where e.event_day > current_date()", nativeQuery = true)
    List<EventInventoryItem> findRentedItems();
    @Query(value = "select ei.* from events_inventory_items ei " +
            "inner join events e on ei.event_id = e.id where e.event_day > current_date() and " +
            "inventory_item_id = :inventoryItemId", nativeQuery = true)
    List<EventInventoryItem> findByInventoryItem(Long inventoryItemId);

    @Query(value = "SELECT SUM(ei.amount) " +
            "FROM events_inventory_items ei " +
            "INNER JOIN events e on ei.event_id = e.id " +
            "INNER JOIN inventory_items i on ei.inventory_item_id = i.id " +
            "WHERE i.id = :inventoryItemId AND e.event_day = :eventDay",
            nativeQuery = true)
    Integer itemsRentedOneDay(Long inventoryItemId, LocalDate eventDay);
}
