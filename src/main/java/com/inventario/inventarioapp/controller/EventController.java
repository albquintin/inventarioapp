package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.ClientDto;
import com.inventario.inventarioapp.dto.EventDto;
import com.inventario.inventarioapp.dto.EventInventoryItemDto;
import com.inventario.inventarioapp.entity.EventInventoryItemId;
import com.inventario.inventarioapp.service.ClientService;
import com.inventario.inventarioapp.service.EventInventoryItemService;
import com.inventario.inventarioapp.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    public EventService eventService;
    public ClientService clientService;
    public EventInventoryItemService eventInventoryItemService;

    public EventController(EventService eventService, ClientService clientService, EventInventoryItemService eventInventoryItemService) {
        this.eventService = eventService;
        this.clientService = clientService;
        this.eventInventoryItemService = eventInventoryItemService;
    }

    @GetMapping("/events/events")
    public String events(Model model){
        List<EventDto> events = eventService.findActiveEvents();
        model.addAttribute("events", events);
        return "/events/events";
    }
    @GetMapping("/events/old_events")
    public String oldEvents(Model model){
        List<EventDto> events = eventService.findOldEvents();
        model.addAttribute("events", events);
        return "/events/old_events";
    }

    @GetMapping("/events/events/newevent")
    public String newClientForm(Model model){
        EventDto eventDto = new EventDto();
        model.addAttribute("event", eventDto);
        List<ClientDto> clients = clientService.findActiveClients();
        model.addAttribute("clients", clients);
        return "/events/create_event";
    }
    @PostMapping("events/events")
    public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("event", eventDto);
            List<ClientDto> clients = clientService.findActiveClients();
            model.addAttribute("clients", clients);
            return "/events/create_event";
        }
        eventDto.setActive(true);
        eventService.createEvent(eventDto);
        return "redirect:/events/events";
    }

    @GetMapping("events/events/edit/{eventId}")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        List<ClientDto> clients = clientService.findActiveClients();
        model.addAttribute("clients", clients);
        return "events/edit_event";
    }

    @PostMapping("events/events/{eventId}")
    public String updateEvent(@PathVariable("eventId") Long eventId,
                              @Valid @ModelAttribute("event") EventDto event,
                              BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("event", event);
            List<ClientDto> clients = clientService.findActiveClients();
            model.addAttribute("clients", clients);
            return "events/edit_event";
        }
        event.setId(eventId);
        eventService.updateEvent(event);
        return "redirect:/events/events";
    }

    @GetMapping("events/events/delete/{eventId}")
    public String logicDeleteEvent(@PathVariable("eventId") Long eventId){
        eventService.logicDeleteEvent(eventId);
        return "redirect:/events/events";
    }
    @GetMapping("events/old_events/delete/{eventId}")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events/old_events";
    }
    @GetMapping("events/old_events/restore/{eventId}")
    public String restoreEvent(@PathVariable("eventId") Long eventId){
        eventService.restoreEvent(eventId);
        return "redirect:/events/events";
    }
    @GetMapping("events/events/view/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        List<EventInventoryItemDto> eventInventoryItemDtos = eventInventoryItemService.findByEvent(eventId);
        model.addAttribute("eventInventoryItems", eventInventoryItemDtos);
        return "events/view_event";
    }

    @GetMapping("events/events/view/delete/{eventId}/{inventoryItemId}")
    public String deleteInventoryItemFromEvent(@PathVariable("inventoryItemId") Long inventoryItemId,
                                               @PathVariable("eventId") Long eventId){
        EventInventoryItemId eventInventoryItemId = new EventInventoryItemId(eventId, inventoryItemId);
        eventInventoryItemService.deleteEventInventoryItemById(eventInventoryItemId);
        return "redirect:/events/events/view/" + eventId;
    }
}
