package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.active = true")
    List<Client> findActiveClients();

    @Query("SELECT c FROM Client c WHERE c.active = false")
    List<Client> findOldClients();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Client c SET c.active = false WHERE c.id = :clientId")
    void logicDeleteById(Long clientId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Client c SET c.active = true WHERE c.id = :clientId")
    void restoreClient(Long clientId);
}
