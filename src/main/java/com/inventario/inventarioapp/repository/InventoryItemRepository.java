package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    @Query("SELECT i FROM InventoryItem i WHERE i.name LIKE CONCAT('%', :name, '%')")
    List<InventoryItem> searchInventoryItemsByName(String name);
}
