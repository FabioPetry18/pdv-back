package com.petry.pdv.funcionario.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import com.petry.pdv.funcionario.repository.FuncionarioRepository;
import com.petry.pdv.funcionario.service.FuncionarioService;
import com.petry.pdv.utils.ErrorResponse;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService service;
	
	@Autowired
	private FuncionarioRepository repository;
	

	
	@GetMapping
	public List<Funcionario> getFuncionariosByCodloja(Long codloja) {
		return service.getFuncionariosByCodloja(codloja);
	}
	@PostMapping
	public ResponseEntity save(@RequestBody Funcionario funcionario) {
		String result = UUID.nameUUIDFromBytes((funcionario.getIdLoja()+funcionario.getNome()).getBytes()).toString();
		Optional<Funcionario> func = repository.findById(result);
		if(func.isPresent()) {
			return new ResponseEntity(new ErrorResponse("Usuário com a combinação loja e nome já vinculado!"), HttpStatus.CONFLICT);
		}else {			
			funcionario.setId(result);
			return new ResponseEntity(service.saveFuncionario(funcionario), HttpStatus.OK);
		}
	}
	
//	@PutMapping
//	public ResponseEntity<?> update(@RequestBody Funcionario funcionario) {
//		if(service.findById(funcionario.getIdFuncionario())) {
//			return ResponseEntity.ok(funcionario);
//		}
//		return new ResponseEntity<>("Não foi possível atualizar o funcionário", HttpStatus.NOT_FOUND);
//	}
	
	
	
	
}
