package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
