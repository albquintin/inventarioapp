package com.inventario.inventarioapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeDto {
    private Long id;
    @NotEmpty(message = "Debes elegir un nombre para el tipo")
    private String name;
}
