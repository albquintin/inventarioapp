package com.inventario.inventarioapp.mapper;

import com.inventario.inventarioapp.dto.EventDto;
import com.inventario.inventarioapp.entity.Client;
import com.inventario.inventarioapp.entity.Event;


public class EventMapper {

    public static EventDto mapToEventDto(Event event){
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .type(event.getType())
                .price(event.getPrice())
                .clientId(event.getClient().getId())
                .clientName(event.getClient().getName())
                .eventDay(event.getEventDay())
                .active(event.getActive())
                .build();
    }

    public static Event mapToEvent(EventDto eventDto, Client client){
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .price(eventDto.getPrice())
                .client(client)
                .eventDay(eventDto.getEventDay())
                .active(eventDto.getActive())
                .build();
    }
}
