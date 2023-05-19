package com.inventario.inventarioapp.repository;

import com.inventario.inventarioapp.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("SELECT t FROM Type t WHERE t.name = :name")
    Type findTypeByName(String name);
}
