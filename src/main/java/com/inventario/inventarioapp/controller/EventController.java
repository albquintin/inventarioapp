package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.ClientDto;
import com.inventario.inventarioapp.dto.EventDto;
import com.inventario.inventarioapp.entity.Event;
import com.inventario.inventarioapp.service.ClientService;
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

    public EventController(EventService eventService, ClientService clientService) {
        this.eventService = eventService;
        this.clientService = clientService;
    }

    @GetMapping("/events/events")
    public String events(Model model){
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        model.addAttribute("title", "Lista de Eventos");
        return "/events/events";
    }
    @GetMapping("/events/past_events")
    public String pastEvents(Model model){
        List<EventDto> pastEvents = eventService.findPastEvents();
        model.addAttribute("events", pastEvents);
        model.addAttribute("title", "Eventos Pasados");
        return "/events/events";
    }
    @GetMapping("/events/coming_events")
    public String comingEvents(Model model){
        List<EventDto> pastEvents = eventService.findComingEvents();
        model.addAttribute("events", pastEvents);
        model.addAttribute("title", "Próximos Eventos");
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

    @GetMapping("events/events/edit/{eventId}")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        List<ClientDto> listClients = clientService.findAllClients();
        model.addAttribute("clients", listClients);
        return "events/edit_event";
    }

    @PostMapping("events/events/{eventId}")
    public String updateEvent(@PathVariable("eventId") Long eventId,
                              @Valid @ModelAttribute("event") EventDto event,
                              BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("event", event);
            return "events/edit_event";
        }
        event.setId(eventId);
        eventService.updateEvent(event);
        return "redirect:/events/events";
    }

    @GetMapping("events/events/delete/{eventId}")
    public String deleteEvent(@PathVariable("eventId") Long eventId, Model model){
        eventService.deleteEvent(eventId);
        return "redirect:/events/events";
    }
    @GetMapping("events/events/view/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "events/view_event";
    }
}
