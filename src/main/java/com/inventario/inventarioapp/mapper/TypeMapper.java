package com.inventario.inventarioapp.mapper;

import com.inventario.inventarioapp.dto.TypeDto;
import com.inventario.inventarioapp.entity.Type;

public class TypeMapper {

    public static TypeDto mapToTypeDto(Type type){
        TypeDto typeDto = TypeDto.builder()
                .id(type.getId())
                .name(type.getName())
                .build();
        return typeDto;
    }

    public static Type mapToType(TypeDto typeDto){
        Type type = Type.builder()
                .id(typeDto.getId())
                .name(typeDto.getName())
                .build();
        return type;
    }
}
