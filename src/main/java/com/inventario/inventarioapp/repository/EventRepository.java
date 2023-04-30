package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.dto.EventDto;
import com.inventario.inventarioapp.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "select * from events where event_day < curdate()", nativeQuery = true)
    List<Event> findPastEvents();

    @Query(value = "select * from events where event_day > curdate()", nativeQuery = true)
    List<Event> findComingEvents();
}
