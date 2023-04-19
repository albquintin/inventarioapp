package com.inventario.inventarioapp.service.impl;

import com.inventario.inventarioapp.dto.TypeDto;
import com.inventario.inventarioapp.entity.Type;
import com.inventario.inventarioapp.mapper.TypeMapper;
import com.inventario.inventarioapp.repository.TypeRepository;
import com.inventario.inventarioapp.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }


    @Override
    public List<TypeDto> findAllTypes() {
        List<Type> types = typeRepository.findAll();
        return types.stream().map((type) -> TypeMapper.mapToTypeDto(type)).collect(Collectors.toList());
    }

    @Override
    public void createType(TypeDto typeDto) {
        Type type = TypeMapper.mapToType(typeDto);
        typeRepository.save(type);
    }
}
