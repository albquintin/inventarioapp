package com.inventario.inventarioapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryItemDto {
    private Long eventId;
    private String eventName;
    private Long inventoryItemId;
    private String inventoryItemName;
    private String inventoryItemPosition;
    private String clientName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDay;
    @NotNull(message = "El número de objetos alquilados debe ser mayor que 0 y menor que el total disponible")
    private Integer amount;
    private Float totalPrice;
}
