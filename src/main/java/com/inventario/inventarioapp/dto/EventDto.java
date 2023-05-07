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
public class EventDto {

    private Long id;
    @NotEmpty(message = "El nombre del evento no puede estar vacío")
    private String name;
    @NotEmpty(message = "El tipo de evento no puede estar vacío")
    private String type;
    @NotNull(message = "El precio del evento no puede estar vacío")
    private Float price;
    private Long clientId;
    private String clientName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDay;
    @NotNull(message = "El primer pago del evento no puede estar vacío")
    private Float firstPayment;
    private Float secondPayment;
    private Float thirdPayment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha del primer pago del evento no puede estar vacía")
    private LocalDate firstPaymentDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate secondPaymentDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate thirdPaymentDay;
    private Boolean active;
}
