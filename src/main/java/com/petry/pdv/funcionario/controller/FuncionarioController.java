package com.petry.pdv.funcionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.funcionario.entity.Funcionario;
import com.petry.pdv.funcionario.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService service;
	
	@GetMapping
	public List<Funcionario> getAll() {
		return service.getAllFuncionarios();
	}
	@PostMapping
	public Funcionario save(@RequestBody Funcionario funcionario) {
		return service.saveFuncionario(funcionario);
	}
	
//	@PutMapping
//	public ResponseEntity<?> update(@RequestBody Funcionario funcionario) {
//		if(service.findById(funcionario.getIdFuncionario())) {
//			return ResponseEntity.ok(funcionario);
//		}
//		return new ResponseEntity<>("Não foi possível atualizar o funcionário", HttpStatus.NOT_FOUND);
//	}
	
	
	
	
}
