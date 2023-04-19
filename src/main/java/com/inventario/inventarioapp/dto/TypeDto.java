package com.inventario.inventarioapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeDto {
    private Long id;
    @NotEmpty(message = "Debes elegir un nombre para el tipo")
    private String name;
}
