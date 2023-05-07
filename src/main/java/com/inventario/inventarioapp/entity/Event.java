package com.inventario.inventarioapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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
    @Column(name = "active")
    private Boolean active;
    @Column(name = "first_payment")
    private Float firstPayment;
    @Column(name = "second_payment")
    private Float secondPayment;
    @Column(name = "third_payment")
    private Float thirdPayment;
    @Column(name = "first_payment_day")
    private LocalDate firstPaymentDay;
    @Column(name = "second_payment_day")
    private LocalDate secondPaymentDay;
    @Column(name = "third_payment_day")
    private LocalDate thirdPaymentDay;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Collection<EventInventoryItem> eventInventoryItems = new ArrayList<>();
}
