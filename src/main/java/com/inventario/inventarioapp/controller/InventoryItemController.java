package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.InventoryItemDto;
import com.inventario.inventarioapp.dto.SubtypeDto;
import com.inventario.inventarioapp.dto.TypeDto;
import com.inventario.inventarioapp.service.InventoryItemService;
import com.inventario.inventarioapp.service.SubtypeService;
import com.inventario.inventarioapp.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class InventoryItemController {

    private InventoryItemService inventoryItemService;
    private SubtypeService subtypeService;
    private TypeService typeService;

    public InventoryItemController(InventoryItemService inventoryItemService, SubtypeService subtypeService, TypeService typeService){
        this.inventoryItemService = inventoryItemService;
        this.subtypeService = subtypeService;
        this.typeService = typeService;
    }

    @GetMapping("/types/inventory_items")
    public String inventoryItems(Model model){
        List<InventoryItemDto> inventoryItems = inventoryItemService.findAllInventoryItems();
        model.addAttribute("inventoryItems", inventoryItems);
        return "/types/inventory_items";
    }

    @GetMapping("/types/inventory_items/newinventoryitem")
    public String newInventoryItemForm(Model model){
        InventoryItemDto inventoryItemDto = new InventoryItemDto();
        model.addAttribute("inventoryItem", inventoryItemDto);
        List<TypeDto> listTypes = typeService.findAllTypes();
        model.addAttribute("listTypes", listTypes);
        List<SubtypeDto> listSubtypes = subtypeService.findAllSubtypes();
        model.addAttribute("listSubtypes", listSubtypes);
        return "/types/create_inventory_item";
    }
    @PostMapping("types/inventory_items")
    public String createInventoryItem(@Valid @ModelAttribute("inventoryItem") InventoryItemDto inventoryItemDto,
                                      BindingResult result, Model model,
                                      @RequestParam("file") MultipartFile picture){

        if(result.hasErrors()){
            model.addAttribute("inventoryItem", inventoryItemDto);
            return "types/create_inventory_item";
        }
        if(!picture.isEmpty()){
            Path imagesDirectory = Paths.get("src//main//resources//static/images");
            String absolutePath = imagesDirectory.toFile().getAbsolutePath();

            try{
                byte[] bytesImg = picture.getBytes();
                Path completePath = Paths.get(absolutePath + "//" + picture.getOriginalFilename());
                Files.write(completePath, bytesImg);
                inventoryItemDto.setPicture(picture.getOriginalFilename());
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        inventoryItemService.createInventoryItem(inventoryItemDto);
        return "redirect:/types/inventory_items";
    }

}
