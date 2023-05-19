package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.TypeDto;
import com.inventario.inventarioapp.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


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
        Optional<TypeDto> foundType = typeService.findByName(typeDto.getName());
        if(foundType.isPresent()){
            model.addAttribute("duplicatedType", true);
            ObjectError error = new ObjectError("type", "duplicated type");
            result.addError(error);
        }
        if(result.hasErrors()){
            model.addAttribute("type", typeDto);
            return "types/create_type";
        }
        typeService.createType(typeDto);
        return "redirect:/types/types";
    }

}
