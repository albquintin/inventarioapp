package com.inventario.inventarioapp.controller;

import com.inventario.inventarioapp.dto.ClientDto;
import com.inventario.inventarioapp.dto.EventDto;
import com.inventario.inventarioapp.dto.EventInventoryItemDto;
import com.inventario.inventarioapp.dto.InventoryItemDto;
import com.inventario.inventarioapp.entity.EventInventoryItemId;
import com.inventario.inventarioapp.service.ClientService;
import com.inventario.inventarioapp.service.EventInventoryItemService;
import com.inventario.inventarioapp.service.EventService;
import com.inventario.inventarioapp.service.InventoryItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    public EventService eventService;
    public ClientService clientService;
    public EventInventoryItemService eventInventoryItemService;
    public InventoryItemService inventoryItemService;

    public EventController(EventService eventService, ClientService clientService, EventInventoryItemService eventInventoryItemService, InventoryItemService inventoryItemService) {
        this.eventService = eventService;
        this.clientService = clientService;
        this.eventInventoryItemService = eventInventoryItemService;
        this.inventoryItemService = inventoryItemService;
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
    public String createEvent(@Valid @ModelAttribute("event") EventDto event,
                              BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("event", event);
            List<ClientDto> clients = clientService.findActiveClients();
            model.addAttribute("clients", clients);
            return "/events/create_event";
        }
        if(!checkPaymentsMatch(event)){
            model.addAttribute("event", event);
            model.addAttribute("priceNotMatch", true);
            List<ClientDto> clients = clientService.findActiveClients();
            model.addAttribute("clients", clients);
            return "events/create_event";
        }
        if(!checkDatesAreInOrder(event)){
            model.addAttribute("event", event);
            model.addAttribute("datesNotMatch", true);
            List<ClientDto> clients = clientService.findActiveClients();
            model.addAttribute("clients", clients);
            return "events/create_event";
        }
        event.setActive(true);
        eventService.createEvent(event);
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
            event.setId(eventId);
            model.addAttribute("event", event);
            List<ClientDto> clients = clientService.findActiveClients();
            model.addAttribute("clients", clients);
            return "events/edit_event";
        }
        if(!checkPaymentsMatch(event)){
            event.setId(eventId);
            model.addAttribute("event", event);
            model.addAttribute("priceNotMatch", true);
            List<ClientDto> clients = clientService.findActiveClients();
            model.addAttribute("clients", clients);
            return "events/edit_event";
        }
        if(!checkDatesAreInOrder(event)){
            event.setId(eventId);
            model.addAttribute("event", event);
            model.addAttribute("datesNotMatch", true);
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
        InventoryItemDto inventoryItemDto = inventoryItemService.findInventoryItemById(inventoryItemId);
        inventoryItemDto.setTimesRented(inventoryItemDto.getTimesRented()-1);
        inventoryItemService.updateInventoryItem(inventoryItemDto);
        return "redirect:/events/events/view/" + eventId;
    }

    public boolean checkPaymentsMatch(EventDto event){
        Optional<Float> secondPayment = Optional.ofNullable(event.getSecondPayment());
        Optional<Float> thirdPayment = Optional.ofNullable(event.getThirdPayment());
        Float paymentsSum = event.getFirstPayment() +
                (secondPayment.isPresent()? secondPayment.get() : 0)+
                (thirdPayment.isPresent()? thirdPayment.get() : 0);

        if(event.getPrice()!=paymentsSum.floatValue())
            return false;
        else
            return true;
    }

    public boolean checkDatesAreInOrder(EventDto event){
        Optional<LocalDate> secondPaymentDate = Optional.ofNullable(event.getSecondPaymentDay());
        Optional<LocalDate> thirdPaymentDate = Optional.ofNullable(event.getThirdPaymentDay());
        if(thirdPaymentDate.isPresent())
            if(thirdPaymentDate.get().isBefore(secondPaymentDate.get())||thirdPaymentDate.get().isBefore(event.getFirstPaymentDay()))
                return false;
        if(secondPaymentDate.isPresent())
            if(secondPaymentDate.get().isBefore(event.getFirstPaymentDay()))
                return false;
        return true;
    }
}
