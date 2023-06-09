package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.TypeDto;

import java.util.List;
import java.util.Optional;

public interface TypeService {

    List<TypeDto> findAllTypes();

    void createType(TypeDto typeDto);

    TypeDto findById(Long typeId);
    Optional<TypeDto> findByName(String name);
}
