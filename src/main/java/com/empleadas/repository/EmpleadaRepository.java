package com.empleadas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empleadas.entities.Empleada;

@Repository
public interface EmpleadaRepository extends JpaRepository<Empleada, String>{

	@Query("SELECT a from Empleada a WHERE a.activo = true ")
	public List<Empleada> buscarActivos();
	
	@Query("SELECT a from Empleada a WHERE a.activo = true AND a.mail LIKE :mail")
	public Empleada buscarMail(@Param("mail") String mail);
	
	@Query("SELECT a from Empleada a WHERE a.dni LIKE :dni")
	public Empleada buscarDni(@Param("dni") Integer dni);
	
}
