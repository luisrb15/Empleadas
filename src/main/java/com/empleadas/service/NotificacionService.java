package com.empleadas.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.empleadas.entities.Empleada;
import com.empleadas.repository.EmpleadaRepository;

@Service
public class NotificacionService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmpleadaRepository empleadaRepository;
	
	public void sendSimpleEmail(String id) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		Empleada empleada = empleadaRepository.getById(id);
		
		message.setFrom("flordelbambu@gmail.com");
		message.setTo("flordelbambu@gmail.com");
		message.setSubject("Ingreso registrado de " + empleada.getApellido() + " " + empleada.getNombre());
		message.setText("Se ha registrado el ingreso de la empleada " + empleada.getApellido() + " " + empleada.getNombre() + " el día " + new Date());
	
		mailSender.send(message);
	}
}
