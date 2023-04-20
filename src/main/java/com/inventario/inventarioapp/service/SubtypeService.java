package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.SubtypeDto;

import java.util.List;

public interface SubtypeService {

    List<SubtypeDto> findAllSubtypes();

    void createSubtype(SubtypeDto subtypeDto);
}
