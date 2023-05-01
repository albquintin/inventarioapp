package com.inventario.inventarioapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "type", length = 20)
    private String type;
    @Column(name = "price")
    private Float price;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "event_day")
    private LocalDate eventDay;
    @OneToMany(mappedBy = "event")
    private Collection<EventInventoryItem> eventInventoryItems = new ArrayList<>();
}
