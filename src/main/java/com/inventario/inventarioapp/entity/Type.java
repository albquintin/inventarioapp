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
@Table(name= "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @OneToMany(mappedBy = "type", cascade = CascadeType.REMOVE)
    private List<InventoryItem> inventoryItems;
}
