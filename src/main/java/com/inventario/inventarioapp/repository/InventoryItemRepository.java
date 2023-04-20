package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
