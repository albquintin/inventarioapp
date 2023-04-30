package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.ClientDto;
import com.inventario.inventarioapp.dto.EventDto;
import com.inventario.inventarioapp.service.ClientService;
import com.inventario.inventarioapp.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    public EventService eventService;
    public ClientService clientService;

    public EventController(EventService eventService, ClientService clientService) {
        this.eventService = eventService;
        this.clientService = clientService;
    }

    @GetMapping("/events/events")
    public String events(Model model){
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "/events/events";
    }
    @GetMapping("/events/events/newevent")
    public String newClientForm(Model model){
        EventDto eventDto = new EventDto();
        model.addAttribute("event", eventDto);
        List<ClientDto> clientList = clientService.findAllClients();
        model.addAttribute("clients", clientList);
        return "/events/create_event";
    }
    @PostMapping("events/events")
    public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("event", eventDto);
            return "/events/create_event";
        }
        eventService.createEvent(eventDto);
        return "redirect:/events/events";
    }

}
