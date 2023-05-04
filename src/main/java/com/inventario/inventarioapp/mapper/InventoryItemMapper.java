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
                .amount(inventoryItem.getAmount())
                .color(inventoryItem.getColor())
                .size(inventoryItem.getSize())
                .typeId(inventoryItem.getType().getId())
                .typeName(inventoryItem.getType().getName())
                .subtypeId(inventoryItem.getSubtype().getId())
                .subtypeName(inventoryItem.getSubtype().getName())
                .picture(inventoryItem.getPicture())
                .active(inventoryItem.getActive())
                .build();
    }

    public static InventoryItem mapToInventoryItem(InventoryItemDto inventoryItemDto, Type type, Subtype subtype){
        return InventoryItem.builder()
                .id(inventoryItemDto.getId())
                .name(inventoryItemDto.getName())
                .position(inventoryItemDto.getPosition())
                .price(inventoryItemDto.getPrice())
                .timesRented(inventoryItemDto.getTimesRented())
                .amount(inventoryItemDto.getAmount())
                .color(inventoryItemDto.getColor())
                .size(inventoryItemDto.getSize())
                .type(type)
                .subtype(subtype)
                .picture(inventoryItemDto.getPicture())
                .active(inventoryItemDto.getActive())
                .build();
    }
}
