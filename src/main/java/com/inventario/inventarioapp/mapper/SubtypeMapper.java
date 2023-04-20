package com.inventario.inventarioapp.mapper;

import com.inventario.inventarioapp.dto.SubtypeDto;
import com.inventario.inventarioapp.entity.Subtype;
import com.inventario.inventarioapp.entity.Type;

public class SubtypeMapper {

    public static SubtypeDto mapToSubtypeDto(Subtype subtype){
        return SubtypeDto.builder()
                .id(subtype.getId())
                .name(subtype.getName())
                .typeId(subtype.getType().getId())
                .typeName(subtype.getType().getName())
                .build();
    }

    public static Subtype mapToSubtype(SubtypeDto subtypeDto, Type type){
        return Subtype.builder()
                .id(subtypeDto.getId())
                .name(subtypeDto.getName())
                .type(type)
                .build();
    }
}
