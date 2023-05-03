package com.inventario.inventarioapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;
    @Column(name = "dni", nullable = false, length = 9, unique = true)
    private String dni;
    @Column(name = "phone_number", nullable = false, length = 12, unique = true)
    private String phoneNumber;
    @Column(name = "address", nullable = false, length = 100)
    private String address;
    @Column(name = "active")
    private Boolean active;
}
