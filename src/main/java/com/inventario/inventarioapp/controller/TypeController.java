package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.TypeDto;
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
public class TypeController {

    private TypeService typeService;

    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    @GetMapping("/types/types")
    public String types(Model model){
        List<TypeDto> types = typeService.findAllTypes();
        model.addAttribute("types", types);
        return "/types/types";
    }

    @GetMapping("/types/types/newtype")
    public String newTypeForm(Model model){
        TypeDto typeDto = new TypeDto();
        model.addAttribute("type", typeDto);
        return "/types/create_type";
    }

    @PostMapping("types/types")
    public String createType(@Valid @ModelAttribute("type") TypeDto typeDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("type", typeDto);
            return "types/create_type";
        }
        typeService.createType(typeDto);
        return "redirect:/types/types";
    }

}
