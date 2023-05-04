package com.inventario.inventarioapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "inventory_items")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "position", nullable = false, unique = true)
    private String position;
    @Column(name = "price", nullable = false, precision=10, scale=2)
    private Float price;
    @Column(name = "times_rented")
    private Integer timesRented;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "color")
    private String color;
    @Column(name = "size")
    private String size;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "subtype_id")
    private Subtype subtype;
    @Column(name = "picture")
    private String picture;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "inventoryItem", cascade = CascadeType.ALL)
    private Collection<EventInventoryItem> eventInventoryItems = new ArrayList<>();
}
