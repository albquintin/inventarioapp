package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.Subtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubtypeRepository extends JpaRepository<Subtype, Long> {

    @Query("SELECT s FROM Subtype s WHERE s.type.id = :typeId")
    List<Subtype>findSubtypesByType(Long typeId);
    @Query("SELECT s FROM Subtype s WHERE s.name = :name AND s.type.id = :typeId")
    Subtype findByName(String name, Long typeId);
}
