package com.inventario.inventarioapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "subtypes")
public class Subtype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany(mappedBy = "subtype", cascade = CascadeType.REMOVE)
    private List<InventoryItem> inventoryItems;

    public Subtype(String name, Type type) {
        this.name = name;
        this.type = type;
    }
}
