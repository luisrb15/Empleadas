package com.empleadas.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empleadas.entities.Empleada;
import com.empleadas.repository.EmpleadaRepository;

@Service
public class EmpleadaService {

	@Autowired
	private EmpleadaRepository empleadaRepository;

	@Transactional
	public Empleada registrar(String nombre, String apellido, String mail, Integer dni) throws Exception {

		validarDni(dni);
		
		Empleada empleada = new Empleada();

		empleada.setNombre(nombre);
		empleada.setApellido(apellido);
		empleada.setMail(mail);
		empleada.setActivo(true);
		empleada.setDni(dni);

		empleadaRepository.save(empleada);

		return empleada;
	}

	@Transactional
	public Empleada modificar(String id, String nombre, String apellido, String mail, Integer dni) {

		Empleada empleada = buscarId(id);

		empleada.setNombre(nombre);
		empleada.setApellido(apellido);
		empleada.setMail(mail);
		empleada.setDni(dni);

		empleadaRepository.save(empleada);

		return empleada;
	}

	@Transactional
	public Empleada alta(String id) {
		Empleada empleada = buscarId(id);
		empleada.setActivo(true);

		empleadaRepository.save(empleada);

		return empleada;
	}
	
	@Transactional
	public Empleada baja(String id) {
		Empleada empleada = buscarId(id);
		empleada.setActivo(false);

		empleadaRepository.save(empleada);

		return empleada;
	}

	@Transactional
	public Empleada buscarId(String id) {

		Optional<Empleada> respuesta = empleadaRepository.findById(id);
		if (respuesta.isPresent()) {
			Empleada empleada = respuesta.get();
			return empleada;
		}
		return null;
	}
	
	@Transactional
	public Empleada buscarMail(String mail) {
		return empleadaRepository.buscarMail(mail);
	}
	
	@Transactional
	public Empleada buscarDni(Integer dni) {
		return empleadaRepository.buscarDni(dni);
	}

	@Transactional
	public List<Empleada> listaEmpleadas() {
		return empleadaRepository.buscarActivos();
	}
	
	private void validarDni (Integer dni) throws Exception {
		Integer documento = empleadaRepository.buscarDni(dni).getDni();
		if (documento == null || documento == 0 || documento.equals(0)) {
			throw new Exception();
		}
	}
}
