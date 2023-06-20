package com.inventario.inventarioapp;

import com.inventario.inventarioapp.entity.Client;
import com.inventario.inventarioapp.entity.InventoryItem;
import com.inventario.inventarioapp.entity.Subtype;
import com.inventario.inventarioapp.entity.Type;
import com.inventario.inventarioapp.mapper.ClientMapper;
import com.inventario.inventarioapp.mapper.InventoryItemMapper;
import com.inventario.inventarioapp.repository.InventoryItemRepository;
import com.inventario.inventarioapp.service.InventoryItemService;
import com.inventario.inventarioapp.service.impl.InventoryItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InventoryItemServiceMockTest {
    @MockBean
    private InventoryItemRepository inventoryItemRepository;
    @Autowired
    private InventoryItemServiceImpl inventoryItemService;
    List<InventoryItem> oldInventoryItemList;
    List<InventoryItem> activeInventoryItemList;
    InventoryItem firstInventoryItem;
    InventoryItem secondInventoryItem;
    InventoryItem thirdInventoryItem;
    Type type;
    Subtype subtype;

    @BeforeEach
    public void beforeEach(){
        oldInventoryItemList = new ArrayList<>();
        activeInventoryItemList = new ArrayList<>();
        type = new Type();
        subtype = new Subtype();
        firstInventoryItem = new InventoryItem();
        secondInventoryItem = new InventoryItem();
        thirdInventoryItem = new InventoryItem();
        firstInventoryItem.setActive(false);
        firstInventoryItem.setType(type);
        firstInventoryItem.setSubtype(subtype);
        secondInventoryItem.setActive(false);
        secondInventoryItem.setType(type);
        secondInventoryItem.setSubtype(subtype);
        thirdInventoryItem.setActive(true);
        thirdInventoryItem.setType(type);
        thirdInventoryItem.setSubtype(subtype);
    }

    @Test
    public void findActiveInventoryItems(){
        activeInventoryItemList.add(thirdInventoryItem);

        when(inventoryItemRepository.findActiveInventoryItems()).thenReturn(activeInventoryItemList);
        assertEquals(activeInventoryItemList.stream().map((inventoryItem) -> InventoryItemMapper.mapToInventoryItemDto(inventoryItem)).collect(Collectors.toList()), inventoryItemService.findActiveInventoryItems());
    }

    @Test
    public void findOldInventoryItems(){
        oldInventoryItemList.add(firstInventoryItem);
        oldInventoryItemList.add(secondInventoryItem);

        when(inventoryItemRepository.findOldInventoryItems()).thenReturn(oldInventoryItemList);
        assertEquals(oldInventoryItemList.stream().map((inventoryItem) -> InventoryItemMapper.mapToInventoryItemDto(inventoryItem)).collect(Collectors.toList()), inventoryItemService.findOldInventoryItems());
    }
}
