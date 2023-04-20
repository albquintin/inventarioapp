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
public class SubtypeDto {

    private Long id;
    @NotEmpty(message = "Debes elegir un nombre para el subtipo")
    private String name;
    private Long typeId;
    private String typeName;
}
