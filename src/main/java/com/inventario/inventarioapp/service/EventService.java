package com.inventario.inventarioapp.service;

import com.inventario.inventarioapp.dto.EventDto;

import java.util.List;

public interface EventService {

    List<EventDto> findAllEvents();
    void createEvent(EventDto eventDto);
}
