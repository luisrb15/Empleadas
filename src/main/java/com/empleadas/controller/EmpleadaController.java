package com.empleadas.controller;

import com.empleadas.entities.Guardia;
import com.empleadas.service.GuardiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empleadas.entities.Empleada;
import com.empleadas.service.EmpleadaService;

@Controller
@RequestMapping("")
public class EmpleadaController {

	@Autowired
	private EmpleadaService empleadaService;

	@Autowired
	private GuardiaService guardiaService;

	@GetMapping("")
	public String ingreso() {
		return "index";
	}

	@PostMapping("")
	public String ingresoCorrecto(ModelMap model, @RequestParam Integer dni) {
		Empleada empleada = empleadaService.buscarDni(dni);
		if (empleada != null) {
			model.addAttribute("empleada", empleada);
			return "presente";
		}
		return "index";
	}

	@GetMapping("/presente/{id}")
	public String darPresente(@PathVariable("id") String id) {
		guardiaService.ingreso(id);
		return "presente-exitoso";
	}

	@GetMapping("/salida/{id}")
	public String salir(Model model, @PathVariable("id") String idEmpleada){
		Guardia guardia = guardiaService.buscarActiva(empleadaService.buscarId(idEmpleada));
		if (guardia != null){
			guardiaService.salida(guardia.getId());
			return "salida-exitosa";
		} else {
			model.addAttribute("message", "No se encontraron guardias activas para el usuario seleccionado");
			return "error";
		}
	}
}
