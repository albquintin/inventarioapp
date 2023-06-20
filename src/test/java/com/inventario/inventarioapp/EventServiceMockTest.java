package com.inventario.inventarioapp;

import com.inventario.inventarioapp.entity.Client;
import com.inventario.inventarioapp.entity.Event;
import com.inventario.inventarioapp.mapper.EventMapper;
import com.inventario.inventarioapp.repository.EventRepository;
import com.inventario.inventarioapp.service.impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventServiceMockTest {
    @MockBean
    private EventRepository eventRepository;
    @Autowired
    private EventServiceImpl eventService;
    List<Event> oldEventList;
    List<Event> activeEventList;
    Event firstEvent;
    Event secondEvent;
    Event thirdEvent;
    Client firstClient;

    @BeforeEach
    public void beforeEach(){
        oldEventList = new ArrayList<>();
        activeEventList = new ArrayList<>();
        firstClient = new Client(1L, "Client", "prueba@email.com", "12345678A", "123464545", "Calle Avenida" , true);
        firstEvent = new Event();
        secondEvent = new Event();
        thirdEvent = new Event();
        firstEvent.setClient(firstClient);
        secondEvent.setClient(firstClient);
        thirdEvent.setClient(firstClient);
        firstEvent.setActive(true);
        secondEvent.setActive(false);
        thirdEvent.setActive(true);
    }

    @Test
    public void findActiveEvents(){
        activeEventList.add(firstEvent);
        activeEventList.add(thirdEvent);

        when(eventRepository.findActiveEvents()).thenReturn(activeEventList);
        assertEquals(activeEventList.stream().map((event) -> EventMapper.mapToEventDto(event)).collect(Collectors.toList()), eventService.findActiveEvents());
    }

    @Test
    public void findOldEvents(){
        oldEventList.add(secondEvent);

        when(eventRepository.findOldEvents()).thenReturn(oldEventList);
        assertEquals(oldEventList.stream().map((event) -> EventMapper.mapToEventDto(event)).collect(Collectors.toList()), eventService.findOldEvents());
    }
}
