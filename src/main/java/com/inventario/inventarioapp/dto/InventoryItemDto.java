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
public class InventoryItemDto {

    private Long id;
    @NotEmpty(message = "Debes elegir un nombre para el objeto")
    private String name;
    @NotEmpty(message = "Debes elegir una posición para el objeto")
    private String position;
    private Float price;
    private Integer timesRented;
    private Integer amount;
    private String color;
    private String size;
    private String availability;
    private Long typeId;
    private String typeName;
    private Long subtypeId;
    private String subtypeName;
    private String picture;

}
