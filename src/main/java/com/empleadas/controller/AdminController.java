package com.empleadas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.empleadas.entities.Empleada;
import com.empleadas.service.EmpleadaService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private EmpleadaService empleadaService;
	
	@GetMapping("/lista")
	public String listaEmpleadas(ModelMap model) {
		List <Empleada> listar = empleadaService.listaEmpleadas();
		model.addAttribute("empleadas", listar);
		return "lista";
	}
	
	@GetMapping("/form")
	public String formulario() {
		return "formulario";
	}
	
	@PostMapping("/form")
	public String subirFormulario(
			ModelMap model, 
			@RequestParam(required = false)String id , 
			@RequestParam String nombre,
			@RequestParam String apellido, 
			@RequestParam String mail,
			@RequestParam String dni)
					throws Exception {
		Integer dni1 = Integer.valueOf(dni);
		if(id == null) {
			empleadaService.registrar(nombre, apellido, mail, dni1);
		}else {
			empleadaService.modificar(id, nombre, apellido, mail, dni1);
		}
		return "redirect:/admin/lista";
	}
	
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable("id") String id) {
		empleadaService.baja(id);
		return "redirect:/admin/lista";
	}
	
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable("id") String id) {
		empleadaService.alta(id);
		return "redirect:/admin/lista";
	}
	
	@GetMapping("/modificar/{id}")
	public String modificar(ModelMap model, @PathVariable("id") String id) {
		Empleada empleada = empleadaService.buscarId(id);
		model.addAttribute("empleada", empleada);
		return "formulario";
	}
	
}
