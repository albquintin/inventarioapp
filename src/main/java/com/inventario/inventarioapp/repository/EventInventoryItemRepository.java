package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.EventInventoryItem;
import com.inventario.inventarioapp.entity.EventInventoryItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventInventoryItemRepository extends JpaRepository<EventInventoryItem, EventInventoryItemId> {

}
