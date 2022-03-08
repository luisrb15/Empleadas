package com.empleadas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empleadas.entities.Empleada;
import com.empleadas.service.EmpleadaService;
import com.empleadas.service.NotificacionService;

@Controller
@RequestMapping("")
public class EmpleadaController {

	@Autowired
	private EmpleadaService empleadaService;

	@Autowired
	private NotificacionService notificacionService;
	
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
	
	@PostMapping("/presente")
	public String darPresente(@RequestParam Empleada empleada) {
		enviarMail(empleada.getId());
		return "presente";
	}
	
	@PostMapping("/mailSender/{id}")
	public void enviarMail(@PathVariable("id") String id) {
		notificacionService.sendSimpleEmail(id);
	}
}
