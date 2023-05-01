package com.inventario.inventarioapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryItemDto {
    private Long eventId;
    private String eventName;
    private Long inventoryItemId;
    @NotNull(message = "El número de objetos alquilados debe ser mayor que 0 y menor que el total disponible")
    private Integer amount;
}
