package com.empleadas.controller;

import java.util.List;

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

@Controller
@RequestMapping("/admin")
public class AdminController {

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
			@RequestParam Integer dni) 
					throws Exception {
		if(id==null) {
			empleadaService.registrar(nombre, apellido, mail, dni);
		}else{
			Empleada empleada = empleadaService.modificar(id, nombre, apellido, mail, dni);
			model.put("empleada", empleada);
		}
		
		return "lista";
	}
	
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable("id") String id) {
		empleadaService.baja(id);
		return "redirect:/admin";
	}
	
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable("id") String id) {
		empleadaService.alta(id);
		return "redirect:/admin";
	}
	
	@GetMapping("/modificar/{id}")
	public String modificar(ModelMap model, @PathVariable("id") String id) {
		Empleada empleada = empleadaService.buscarId(id);
		model.addAttribute("empleada", empleada);
		return "formulario";
	}
	
}
