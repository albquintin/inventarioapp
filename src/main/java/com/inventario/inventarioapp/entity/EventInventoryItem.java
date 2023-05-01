package com.inventario.inventarioapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "events_inventory_items")
public class EventInventoryItem implements Serializable {
    @EmbeddedId
    private EventInventoryItemId id = new EventInventoryItemId();
    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @MapsId("inventoryItemId")
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;
    @Column(name = "amount", nullable = false)
    private Integer amount;
}
