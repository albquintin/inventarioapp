package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.dto.EventDto;
import com.inventario.inventarioapp.entity.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.active = true")
    List<Event> findActiveEvents();
    @Query("SELECT e FROM Event e WHERE e.active = false")
    List<Event> findOldEvents();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Event e SET e.active = false WHERE e.id = :eventId")
    void logicDeleteById(Long eventId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Event e SET e.active = true WHERE e.id = :eventId")
    void restoreEvent(Long eventId);
}
