package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.EventDto;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    List<EventDto> findActiveEvents();
    List<EventDto> findOldEvents();
    void createEvent(EventDto eventDto);
    void updateEvent(EventDto eventDto);
    EventDto findEventById(Long eventId);
    void logicDeleteEvent(Long eventId);
    void deleteEvent(Long eventId);
    void restoreEvent(Long eventId);
}
