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
    public List<EventDto> findActiveEvents() {
        List<Event> events = eventRepository.findActiveEvents();
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }
    @Override
    public List<EventDto> findOldEvents() {
        List<Event> events = eventRepository.findOldEvents();
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public void createEvent(EventDto eventDto) {
        Client client = clientRepository.findById(eventDto.getClientId()).get();
        Event event = EventMapper.mapToEvent(eventDto, client);
        eventRepository.save(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Client client = clientRepository.findById(eventDto.getClientId()).get();
        Event event = EventMapper.mapToEvent(eventDto, client);
        eventRepository.save(event);
    }

    @Override
    public EventDto findEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return EventMapper.mapToEventDto(event);
    }

    @Override
    public void logicDeleteEvent(Long eventId) {
        eventRepository.logicDeleteById(eventId);
    }
    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
    @Override
    public void restoreEvent(Long eventId) {
        eventRepository.restoreEvent(eventId);
    }

}
