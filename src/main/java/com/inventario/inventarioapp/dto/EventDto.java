package com.inventario.inventarioapp.dto;

import jakarta.validation.constraints.NotEmpty;
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
    private Float price;
    private Long clientId;
    private String clientName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDay;
    private Boolean active;
}
