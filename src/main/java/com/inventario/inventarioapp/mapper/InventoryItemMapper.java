package com.inventario.inventarioapp.mapper;

import com.inventario.inventarioapp.dto.InventoryItemDto;
import com.inventario.inventarioapp.entity.InventoryItem;
import com.inventario.inventarioapp.entity.Subtype;
import com.inventario.inventarioapp.entity.Type;

public class InventoryItemMapper {

    public static InventoryItemDto mapToInventoryItemDto(InventoryItem inventoryItem){
        return InventoryItemDto.builder()
                .id(inventoryItem.getId())
                .name(inventoryItem.getName())
                .position(inventoryItem.getPosition())
                .price(inventoryItem.getPrice())
                .timesRented(inventoryItem.getTimesRented())
                .color(inventoryItem.getColor())
                .size(inventoryItem.getSize())
                .availability(inventoryItem.getAvailability())
                .typeId(inventoryItem.getType().getId())
                .typeName(inventoryItem.getType().getName())
                .subtypeId(inventoryItem.getSubtype().getId())
                .subtypeName(inventoryItem.getSubtype().getName())
                .picture(inventoryItem.getPicture())
                .build();
    }

    public static InventoryItem mapToInventoryItem(InventoryItemDto inventoryItemDto, Type type, Subtype subtype){
        return InventoryItem.builder()
                .id(inventoryItemDto.getId())
                .name(inventoryItemDto.getName())
                .position(inventoryItemDto.getPosition())
                .price(inventoryItemDto.getPrice())
                .timesRented(inventoryItemDto.getTimesRented())
                .color(inventoryItemDto.getColor())
                .size(inventoryItemDto.getSize())
                .availability(inventoryItemDto.getAvailability())
                .type(type)
                .subtype(subtype)
                .picture(inventoryItemDto.getPicture())
                .build();
    }
}
