package com.inventario.inventarioapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "events_inventory_items",
            joinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "inventory_item_id", referencedColumnName = "id")}
    )
    private List<InventoryItem> inventoryItems = new ArrayList<>();
}
