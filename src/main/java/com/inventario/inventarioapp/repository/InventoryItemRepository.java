package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.InventoryItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    @Query("SELECT i FROM InventoryItem i WHERE i.name LIKE CONCAT('%', :name, '%')")
    List<InventoryItem> searchInventoryItemsByName(String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update inventory_items set times_rented = times_rented + 1 WHERE id = :inventoryItemId", nativeQuery = true)
    void increaseOneTimesRentedInventoryItem(Long inventoryItemId);
}
