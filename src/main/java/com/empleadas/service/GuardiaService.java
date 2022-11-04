package com.empleadas.service;

import com.empleadas.entities.Guardia;
import com.empleadas.entities.Empleada;
import com.empleadas.repository.GuardiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GuardiaService {
    @Autowired
    private GuardiaRepository guardiaRepository;
    @Autowired
    private EmpleadaService empleadaService;

    @Transactional
    public void ingreso (String idEmpleada) {
        Guardia guardia = new Guardia();
        Empleada empleada = empleadaService.buscarId(idEmpleada);
        guardia.setEmpleada(empleada);

        guardiaRepository.save(guardia);
    }

    @Transactional
    public void salida (String idIngreso) {
        Guardia guardia = buscarId(idIngreso);
        guardia.setSalida(new Date());
        guardia.setActiva(false);
    }

    @Transactional
    public Guardia buscarId(String id) {
        Optional<Guardia> respuesta = guardiaRepository.findById(id);
        return respuesta.orElse(null);
    }

    @Transactional
    public Guardia buscarActiva(Empleada empleada) {
        return guardiaRepository.findByEmpleadaAndActiva(empleada);
    }

    @Transactional
    public List<Guardia> listarActivas(){
        return guardiaRepository.findByActiva();
    }

    @Transactional
    public List<Guardia> listarTodas(){
        return guardiaRepository.findAll();
    }
}
