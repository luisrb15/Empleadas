package com.empleadas.repository;

import com.empleadas.entities.Empleada;
import com.empleadas.entities.Guardia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuardiaRepository extends JpaRepository<Guardia, String> {
    @Query("SELECT g FROM Guardia g WHERE g.empleada LIKE :empleada AND g.activa = true")
    public Guardia findByEmpleadaAndActiva(Empleada empleada);

    @Query("SELECT g FROM Guardia g WHERE g.activa = true")
    public List<Guardia> findByActiva();
}
