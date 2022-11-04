package com.empleadas.controller;

import com.empleadas.entities.Guardia;
import com.empleadas.service.GuardiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/guardias")
public class GuardiaController {

    @Autowired
    GuardiaService guardiaService;

    @GetMapping("")
    public String mostrarGuardiasActivas(Model model){
        List<Guardia> guardiasActivas = guardiaService.listarActivas();
        if (!guardiasActivas.isEmpty()) {
            model.addAttribute("guardiasActivas", guardiasActivas);
            return "guardias-activas";
        } else {
            model.addAttribute("message", "No hay guardias activas");
            return "error";
        }
    }

    @GetMapping("historial")
    public String mostrarTodas(Model model){
        List<Guardia> guardias = guardiaService.listarTodas();
        if (!guardias.isEmpty()){
            model.addAttribute("guardias", guardias);
            return "historial-de-guardias";
        } else {
            model.addAttribute("message", "No se encontraron guardias");
            return "error";
        }
    }

}
