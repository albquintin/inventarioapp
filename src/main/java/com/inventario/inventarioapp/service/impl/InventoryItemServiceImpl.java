package com.inventario.inventarioapp.service.impl;

import com.inventario.inventarioapp.dto.InventoryItemDto;
import com.inventario.inventarioapp.entity.InventoryItem;
import com.inventario.inventarioapp.entity.Subtype;
import com.inventario.inventarioapp.entity.Type;
import com.inventario.inventarioapp.mapper.InventoryItemMapper;
import com.inventario.inventarioapp.repository.InventoryItemRepository;
import com.inventario.inventarioapp.repository.SubtypeRepository;
import com.inventario.inventarioapp.repository.TypeRepository;
import com.inventario.inventarioapp.service.InventoryItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

    private SubtypeRepository subtypeRepository;
    private TypeRepository typeRepository;
    private InventoryItemRepository inventoryItemRepository;

    public InventoryItemServiceImpl(SubtypeRepository subtypeRepository, TypeRepository typeRepository, InventoryItemRepository inventoryItemRepository){
        this.subtypeRepository = subtypeRepository;
        this.typeRepository = typeRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }
    @Override
    public List<InventoryItemDto> findActiveInventoryItems() {
        List<InventoryItem> inventoryItems = inventoryItemRepository.findActiveInventoryItems();
        return inventoryItems.stream().map((inventoryItem) -> InventoryItemMapper.mapToInventoryItemDto(inventoryItem)).collect(Collectors.toList());
    }
    @Override
    public List<InventoryItemDto> findOldInventoryItems() {
        List<InventoryItem> inventoryItems = inventoryItemRepository.findOldInventoryItems();
        return inventoryItems.stream().map((inventoryItem) -> InventoryItemMapper.mapToInventoryItemDto(inventoryItem)).collect(Collectors.toList());
    }

    @Override
    public void createInventoryItem(InventoryItemDto inventoryItemDto) {
        Optional<Type> type = typeRepository.findById(inventoryItemDto.getTypeId());
        Optional<Subtype> subtype = subtypeRepository.findById(inventoryItemDto.getSubtypeId());
        InventoryItem inventoryItem = InventoryItemMapper.mapToInventoryItem(inventoryItemDto, type.get(), subtype.get());
        inventoryItemRepository.save(inventoryItem);
    }

    @Override
    public void updateInventoryItem(InventoryItemDto inventoryItemDto) {
        Optional<Type> type = typeRepository.findById(inventoryItemDto.getTypeId());
        Optional<Subtype> subtype = subtypeRepository.findById(inventoryItemDto.getSubtypeId());
        InventoryItem inventoryItem = InventoryItemMapper.mapToInventoryItem(inventoryItemDto, type.get(), subtype.get());
        inventoryItemRepository.save(inventoryItem);
    }

    @Override
    public InventoryItemDto findInventoryItemById(Long inventoryItemId) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId).get();
        return InventoryItemMapper.mapToInventoryItemDto(inventoryItem);
    }

    @Override
    public void logicDeleteInventoryItem(Long inventoryItemId) {
        inventoryItemRepository.logicDeleteById(inventoryItemId);
    }
    @Override
    public void deleteInventoryItem(Long inventoryItemId) {
        inventoryItemRepository.deleteById(inventoryItemId);
    }
    @Override
    public void restoreInventoryItem(Long inventoryItemId) {
        inventoryItemRepository.restoreInventoryItem(inventoryItemId);
    }

    @Override
    public void increaseOneTimesRentedInventoryItem(Long inventoryItemId) {
        inventoryItemRepository.increaseOneTimesRentedInventoryItem(inventoryItemId);
    }

    @Override
    public Optional<InventoryItemDto> findInventoryItemByPosition(String position) {
        InventoryItem inventoryItem = inventoryItemRepository.findInventoryItemByPosition(position);
        if(inventoryItem == null)
            return Optional.empty();
        else
            return Optional.of(InventoryItemMapper.mapToInventoryItemDto(inventoryItem));

    }
}
