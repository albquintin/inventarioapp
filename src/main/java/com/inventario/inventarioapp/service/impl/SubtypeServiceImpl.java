package com.inventario.inventarioapp.service.impl;

import com.inventario.inventarioapp.dto.SubtypeDto;
import com.inventario.inventarioapp.entity.Subtype;
import com.inventario.inventarioapp.entity.Type;
import com.inventario.inventarioapp.mapper.SubtypeMapper;
import com.inventario.inventarioapp.repository.SubtypeRepository;
import com.inventario.inventarioapp.repository.TypeRepository;
import com.inventario.inventarioapp.service.SubtypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubtypeServiceImpl implements SubtypeService {

    private SubtypeRepository subtypeRepository;
    private TypeRepository typeRepository;

    public SubtypeServiceImpl(SubtypeRepository subtypeRepository, TypeRepository typeRepository){
        this.subtypeRepository = subtypeRepository;
        this.typeRepository = typeRepository;
    }
    @Override
    public List<SubtypeDto> findAllSubtypes() {
        List<Subtype> subtypes = subtypeRepository.findAll();
        return subtypes.stream().map(SubtypeMapper::mapToSubtypeDto).collect(Collectors.toList());
    }

    @Override
    public void createSubtype(SubtypeDto subtypeDto) {
        Optional<Type> type = typeRepository.findById(subtypeDto.getTypeId());
        Subtype subtype = SubtypeMapper.mapToSubtype(subtypeDto, type.get());
        subtypeRepository.save(subtype);
    }

    @Override
    public List<SubtypeDto> findSubtypesByType(Long typeId) {
        List<Subtype>subtypes = subtypeRepository.findSubtypesByType(typeId);
        return subtypes.stream().map(SubtypeMapper::mapToSubtypeDto).collect(Collectors.toList());
    }
}
