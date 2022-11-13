package com.empleadas.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empleadas.entities.Empleada;
import com.empleadas.repository.EmpleadaRepository;

@Service
public class EmpleadaService {

	Logger logger = LoggerFactory.getLogger(EmpleadaService.class);

	@Autowired
	private EmpleadaRepository empleadaRepository;

	@Transactional
	public void registrar(String nombre, String apellido, String mail, Integer dni) throws Exception {
		
		Empleada empleada = new Empleada();

		empleada.setId(generarId());
		empleada.setNombre(nombre);
		empleada.setApellido(apellido);
		empleada.setMail(mail);
		empleada.setDni(dni);
		logger.info("Empleada {} {} registrada", empleada.getNombre(), empleada.getApellido());
		empleadaRepository.save(empleada);
	}

	@Transactional
	public void modificar(String id, String nombre, String apellido, String mail, Integer dni) {

		Optional<Empleada> respuesta = empleadaRepository.findById(id);
		if (respuesta.isPresent()){
			Empleada empleada = respuesta.get();
			logger.info("Empleada {} {}, DNI {}, mail {}, activa = {}, con ID {}",empleada.getNombre(), empleada.getApellido(), empleada.getDni(), empleada.getMail(), empleada.getActivo(), empleada.getId());
			empleada.setNombre(nombre);
			empleada.setApellido(apellido);
			empleada.setMail(mail);
			empleada.setDni(dni);
			logger.info("Empleada {} {}, DNI {}, mail {}, activa = {}, con ID {}",empleada.getNombre(), empleada.getApellido(), empleada.getDni(), empleada.getMail(), empleada.getActivo(), empleada.getId());
			empleadaRepository.save(empleada);
		} else {
			logger.error("No se encontró la empleada {} {}", nombre, apellido);
			throw new RuntimeException("No se encontró la empleada");
		}
	}

	@Transactional
	public void alta(String id) {
		Empleada empleada = buscarId(id);
		empleada.setActivo(true);
		logger.info("Empleada {} {} cambió su estado de activa a = {}", empleada.getNombre(), empleada.getApellido(), empleada.getActivo());
	}
	
	@Transactional
	public void baja(String id) {
		Empleada empleada = buscarId(id);
		empleada.setActivo(false);
		logger.info("Empleada {} {} cambió su estado de activa a = {}", empleada.getNombre(), empleada.getApellido(), empleada.getActivo());
	}

	@Transactional
	public Empleada buscarId(String id) {

		Optional<Empleada> respuesta = empleadaRepository.findById(id);
		return respuesta.orElse(null);
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
		if (documento == null || documento == 0) {
			throw new Exception();
		}
	}

	//id generator
	@Transactional
	public String generarId() {
		String id = "";
		List<Empleada> empleadas = listaEmpleadas();
		if (empleadas.size() == 0) {
			id = "1";
		} else {
			id = String.valueOf(empleadas.size() + 1);
		}
		return id;
	}

	//@Transactional
	//public void ingresar(String idEmpleada) {
	//	calendarService.ingreso(idEmpleada);
	//}

	//@Transactional
	//public void salir(String idCalendar) {
	//	calendarService.salida(idCalendar);
	//}
}
