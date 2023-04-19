package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.TypeDto;
import com.inventario.inventarioapp.entity.Type;

import java.util.List;

public interface TypeService {

    List<TypeDto> findAllTypes();

    void createType(TypeDto typeDto);
}
