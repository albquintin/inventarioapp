package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.SubtypeDto;
import com.inventario.inventarioapp.dto.TypeDto;
import com.inventario.inventarioapp.entity.Type;
import com.inventario.inventarioapp.service.SubtypeService;
import com.inventario.inventarioapp.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SubtypeController {

    private SubtypeService subtypeService;
    private TypeService typeService;

    public SubtypeController(SubtypeService subtypeService, TypeService typeService){
        this.subtypeService = subtypeService;
        this.typeService = typeService;
    }

    @GetMapping("/types/subtypes")
    public String subtypes(Model model){
        List<SubtypeDto> subtypes = subtypeService.findAllSubtypes();
        model.addAttribute("subtypes", subtypes);
        return "/types/subtypes";
    }

    @GetMapping("/types/subtypes/newsubtype")
    public String newSubtypeForm(Model model){
        SubtypeDto subtypeDto = new SubtypeDto();
        model.addAttribute("subtype", subtypeDto);
        List<TypeDto> listTypes = typeService.findAllTypes();
        model.addAttribute("listTypes", listTypes);
        return "/types/create_subtype";
    }

    @PostMapping("types/subtypes")
    public String createType(@Valid @ModelAttribute("subtype") SubtypeDto subtypeDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("subtype", subtypeDto);
            return "types/create_subtype";
        }
        subtypeService.createSubtype(subtypeDto);
        return "redirect:/types/subtypes";
    }
}
