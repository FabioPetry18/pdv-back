package com.petry.pdv.endereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.endereco.entity.Endereco;
import com.petry.pdv.endereco.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping("/:idCliente")
	public List<Endereco> getEnderecoByCliente(@PathVariable Long idCliente ){
		
		return service.buscarEnderecoClienteById(idCliente);
		
	}
	
}
