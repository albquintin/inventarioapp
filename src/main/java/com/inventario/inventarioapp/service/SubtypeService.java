package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.SubtypeDto;

import java.util.List;
import java.util.Optional;

public interface SubtypeService {

    List<SubtypeDto> findAllSubtypes();

    void createSubtype(SubtypeDto subtypeDto);

    List<SubtypeDto> findSubtypesByType(Long typeId);

    Optional<SubtypeDto> findByName(String name, Long typeId);
}
