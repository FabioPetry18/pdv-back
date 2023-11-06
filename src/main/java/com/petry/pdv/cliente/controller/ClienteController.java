package com.petry.pdv.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.cliente.entity.Cliente;
import com.petry.pdv.cliente.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService service;
	
	@PostMapping
	public Cliente insert(@RequestBody Cliente cliente) {
		
		return service.addCliente(cliente);
		
		
	}
	
}
