package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.EventDto;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    List<EventDto> findAllEvents();
    void createEvent(EventDto eventDto);
    void updateEvent(EventDto eventDto);
    EventDto findEventById(Long eventId);
    void deleteEvent(Long eventId);
    List<EventDto> findPastEvents();
    List<EventDto> findComingEvents();
}
