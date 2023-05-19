package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.*;
import com.inventario.inventarioapp.entity.InventoryItem;
import com.inventario.inventarioapp.mapper.InventoryItemMapper;
import com.inventario.inventarioapp.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class InventoryItemController {

    private InventoryItemService inventoryItemService;
    private SubtypeService subtypeService;
    private TypeService typeService;
    private EventService eventService;
    private EventInventoryItemService eventInventoryItemService;

    public InventoryItemController(InventoryItemService inventoryItemService, SubtypeService subtypeService,
                                   TypeService typeService, EventService eventService,
                                   EventInventoryItemService eventInventoryItemService){
        this.inventoryItemService = inventoryItemService;
        this.subtypeService = subtypeService;
        this.typeService = typeService;
        this.eventService = eventService;
        this.eventInventoryItemService = eventInventoryItemService;
    }

    @GetMapping("/items/inventory_items")
    public String inventoryItems(Model model){
        List<InventoryItemDto> inventoryItems = inventoryItemService.findActiveInventoryItems();
        model.addAttribute("inventoryItems", inventoryItems);
        return "/items/inventory_items";
    }

    @GetMapping("/items/old_inventory_items")
    public String oldInventoryItems(Model model){
        List<InventoryItemDto> inventoryItems = inventoryItemService.findOldInventoryItems();
        model.addAttribute("inventoryItems", inventoryItems);
        return "/items/old_inventory_items";
    }

    @GetMapping("/items/rented_inventory_items")
    public String rentedInventoryItems(Model model){
        List<EventInventoryItemDto> eventInventoryItems = eventInventoryItemService.findRentedItems();
        model.addAttribute("eventInventoryItems", eventInventoryItems);
        return "/items/rented_inventory_items";
    }

    @GetMapping("/items/inventory_items/newinventoryitem")
    public String newInventoryItemForm(Model model){
        InventoryItemDto inventoryItemDto = new InventoryItemDto();
        model.addAttribute("inventoryItem", inventoryItemDto);
        List<TypeDto> types = typeService.findAllTypes();
        model.addAttribute("types", types);
        return "/items/create_inventory_item";
    }
    @PostMapping("items/inventory_items")
    public String createInventoryItem(@Valid @ModelAttribute("inventoryItem") InventoryItemDto inventoryItem,
                                      BindingResult result, Model model,
                                      @RequestParam("file") MultipartFile picture){
        Optional<InventoryItemDto> foundInventoryItem = inventoryItemService.findInventoryItemByPosition(inventoryItem.getPosition());
        if(foundInventoryItem.isPresent()){
            model.addAttribute("duplicatedPosition", true);
            ObjectError error = new ObjectError("position", "duplicated position");
            result.addError(error);
        }
        if(result.hasErrors()){
            model.addAttribute("inventoryItem", inventoryItem);
            List<TypeDto> types = typeService.findAllTypes();
            model.addAttribute("types", types);
            return "items/create_inventory_item";
        }
        if(!picture.isEmpty()){
            Path imagesDirectory = Paths.get("src//main//resources//static/images");
            String absolutePath = imagesDirectory.toFile().getAbsolutePath();

            try{
                byte[] bytesImg = picture.getBytes();
                Path completePath = Paths.get(absolutePath + "//" + picture.getOriginalFilename());
                Files.write(completePath, bytesImg);
                inventoryItem.setPicture(picture.getOriginalFilename());
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        inventoryItem.setTimesRented(0);
        inventoryItem.setActive(true);
        inventoryItemService.createInventoryItem(inventoryItem);
        return "redirect:/items/inventory_items";
    }
    @GetMapping("items/inventory_items/edit/{inventoryItemId}")
    public String editInventoryItemForm(@PathVariable("inventoryItemId") Long inventoryItemId, Model model){
        InventoryItemDto inventoryItemDto = inventoryItemService.findInventoryItemById(inventoryItemId);
        model.addAttribute("inventoryItem", inventoryItemDto);
        List<TypeDto> types = typeService.findAllTypes();
        model.addAttribute("types", types);
        List<SubtypeDto> subtypes = subtypeService.findSubtypesByType(inventoryItemDto.getTypeId());
        model.addAttribute("subtypes", subtypes);
        return "items/edit_inventory_item";
    }

    @PostMapping("items/inventory_items/{inventoryItemId}")
    public String updateInventoryItem(@PathVariable("inventoryItemId") Long inventoryItemId,
                                      @Valid @ModelAttribute("inventoryItem") InventoryItemDto inventoryItem,
                                      BindingResult result, Model model,
                                      @RequestParam("file") MultipartFile picture){
        Optional<InventoryItemDto> foundInventoryItem = inventoryItemService.findInventoryItemByPosition(inventoryItem.getPosition());
        if(foundInventoryItem.isPresent() && foundInventoryItem.get().getId() != inventoryItemId){
            model.addAttribute("duplicatedPosition", true);
            ObjectError error = new ObjectError("position", "duplicated position");
            result.addError(error);
        }
        if(result.hasErrors()){
            inventoryItem.setId(inventoryItemId);
            model.addAttribute("inventoryItem", inventoryItem);
            List<TypeDto> types = typeService.findAllTypes();
            model.addAttribute("types", types);
            List<SubtypeDto> subtypes = subtypeService.findSubtypesByType(inventoryItem.getTypeId());
            model.addAttribute("subtypes", subtypes);
            return "items/edit_inventory_item";
        }
        if(!picture.isEmpty()){
            Path imagesDirectory = Paths.get("src//main//resources//static/images");
            String absolutePath = imagesDirectory.toFile().getAbsolutePath();

            try{
                byte[] bytesImg = picture.getBytes();
                Path completePath = Paths.get(absolutePath + "//" + picture.getOriginalFilename());
                Files.write(completePath, bytesImg);
                inventoryItem.setPicture(picture.getOriginalFilename());
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        inventoryItem.setId(inventoryItemId);
        inventoryItemService.updateInventoryItem(inventoryItem);
        return "redirect:/items/inventory_items";
    }
    @GetMapping("items/inventory_items/delete/{inventoryItemId}")
    public String deleteInventoryItem(@PathVariable("inventoryItemId") Long inventoryItemId){
        inventoryItemService.logicDeleteInventoryItem(inventoryItemId);
        return "redirect:/items/inventory_items";
    }

    @GetMapping("items/old_inventory_items/restore/{inventoryItemId}")
    public String restoreInventoryItem(@PathVariable("inventoryItemId") Long inventoryItemId, Model model){
        InventoryItemDto inventoryItem = inventoryItemService.findInventoryItemById(inventoryItemId);
        Optional<InventoryItemDto> foundInventoryItem = inventoryItemService.findInventoryItemByPosition(inventoryItem.getPosition());
        if(foundInventoryItem.isPresent()){
            List<InventoryItemDto> inventoryItems = inventoryItemService.findOldInventoryItems();
            model.addAttribute("inventoryItems", inventoryItems);
            model.addAttribute("duplicatedPosition", true);
            model.addAttribute("position", inventoryItem.getPosition());
            return "items/old_inventory_items";
        }
        inventoryItemService.restoreInventoryItem(inventoryItemId);
        return "redirect:/items/inventory_items";
    }

    @GetMapping("items/old_inventory_items/delete/{inventoryItemId}")
    public String deleteOldInventoryItem(@PathVariable("inventoryItemId") Long inventoryItemId){
        inventoryItemService.deleteInventoryItem(inventoryItemId);
        return "redirect:/items/old_inventory_items";
    }

    @GetMapping("/items/inventory_items/view/{inventoryItemId}")
    public String viewInventoryItem(@PathVariable("inventoryItemId") Long inventoryItemId, Model model){
        InventoryItemDto inventoryItemDto = inventoryItemService.findInventoryItemById(inventoryItemId);
        model.addAttribute("inventoryItem", inventoryItemDto);
        model.addAttribute("isNew", true);
        List<EventInventoryItemDto> eventInventoryItemDtos = eventInventoryItemService.findByInventoryItem(inventoryItemId);
        model.addAttribute("eventInventoryItems", eventInventoryItemDtos);
        return "items/view_inventory_item";
    }

    @GetMapping("/items/old_inventory_items/view/{inventoryItemId}")
    public String viewOldInventoryItem(@PathVariable("inventoryItemId") Long inventoryItemId, Model model){
        InventoryItemDto inventoryItemDto = inventoryItemService.findInventoryItemById(inventoryItemId);
        model.addAttribute("inventoryItem", inventoryItemDto);
        return "items/view_inventory_item";
    }

    @GetMapping("items/inventory_items/rent/{inventoryItemId}")
    public String rentInventoryItemForm(@PathVariable("inventoryItemId") Long inventoryItemId, Model model){
        EventInventoryItemDto eventInventoryItemDto = new EventInventoryItemDto();
        model.addAttribute("eventInventoryItem", eventInventoryItemDto);
        InventoryItemDto inventoryItemDto = inventoryItemService.findInventoryItemById(inventoryItemId);
        model.addAttribute("inventoryItem", inventoryItemDto);
        List<EventDto> events = eventService.findActiveEvents();
        model.addAttribute("events", events);
        return "items/rent_inventory_item";
    }

    @PostMapping("items/rent_inventory_item/{inventoryItemId}")
    public String rentInventoryItem(@PathVariable("inventoryItemId") Long inventoryItemId,
                                    @Valid @ModelAttribute("eventInventoryItem") EventInventoryItemDto eventInventoryItemDto,
                                    BindingResult result, Model model){
        InventoryItemDto inventoryItem = inventoryItemService.findInventoryItemById(inventoryItemId);
        EventDto event = eventService.findEventById(eventInventoryItemDto.getEventId());
        Integer itemsRentedThisDay = eventInventoryItemService.itemsRentedOneDay(inventoryItemId, event.getEventDay())!=null?
                eventInventoryItemService.itemsRentedOneDay(inventoryItemId, event.getEventDay()):0;

        if(result.hasErrors() || itemsRentedThisDay + eventInventoryItemDto.getAmount() > inventoryItem.getAmount()
            || eventInventoryItemDto.getAmount() < 1){
            model.addAttribute("eventInventoryItem", eventInventoryItemDto);
            model.addAttribute("inventoryItem", inventoryItem);
            List<EventDto> events = eventService.findActiveEvents();
            model.addAttribute("events", events);
            model.addAttribute("wrongAmount", true);
            return "items/rent_inventory_item";
        }
        eventInventoryItemDto.setInventoryItemId(inventoryItemId);
        inventoryItemService.increaseOneTimesRentedInventoryItem(inventoryItemId);
        eventInventoryItemService.rentInventoryItem(eventInventoryItemDto);
        return "redirect:/items/inventory_items";
    }
}
