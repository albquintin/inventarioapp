package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.InventoryItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    @Query("SELECT i FROM InventoryItem i WHERE i.active = true")
    List<InventoryItem> findActiveInventoryItems();
    @Query("SELECT i FROM InventoryItem i WHERE i.active = false")
    List<InventoryItem> findOldInventoryItems();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update inventory_items set times_rented = times_rented + 1 WHERE id = :inventoryItemId", nativeQuery = true)
    void increaseOneTimesRentedInventoryItem(Long inventoryItemId);
    @Query("SELECT i FROM InventoryItem i WHERE i.position = :position AND i.active = true")
    InventoryItem findByPosition(String position);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE InventoryItem i SET i.active = false WHERE i.id = :inventoryItemId")
    void logicDeleteById(Long inventoryItemId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE InventoryItem i SET i.active = true WHERE i.id = :inventoryItemId")
    void restoreInventoryItem(Long inventoryItemId);
}
