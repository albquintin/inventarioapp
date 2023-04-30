package com.inventario.inventarioapp.service.impl;

import com.inventario.inventarioapp.dto.EventDto;
import com.inventario.inventarioapp.entity.Client;
import com.inventario.inventarioapp.entity.Event;
import com.inventario.inventarioapp.mapper.EventMapper;
import com.inventario.inventarioapp.repository.ClientRepository;
import com.inventario.inventarioapp.repository.EventRepository;
import com.inventario.inventarioapp.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClientRepository clientRepository;

    public EventServiceImpl(EventRepository eventRepository, ClientRepository clientRepository) {
        this.eventRepository = eventRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public void createEvent(EventDto eventDto) {
        Client client = clientRepository.findById(eventDto.getClientId()).get();
        Event event = EventMapper.mapToEvent(eventDto, client);
        eventRepository.save(event);
    }
}
