package com.inventario.inventarioapp.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EventInventoryItemId implements Serializable {
    private Long eventId;
    private Long inventoryItemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventInventoryItemId that = (EventInventoryItemId) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(inventoryItemId, that.inventoryItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, inventoryItemId);
    }
}
