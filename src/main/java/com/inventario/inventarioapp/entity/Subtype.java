package com.inventario.inventarioapp.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Subtype(String name, Type type) {
        this.name = name;
        this.type = type;
    }
}
