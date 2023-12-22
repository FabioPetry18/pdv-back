package com.petry.pdv.caixa.controller;

import java.util.Date;
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

import com.petry.pdv.caixa.entity.Caixa;
import com.petry.pdv.caixa.service.CaixaService;

@RestController
@RequestMapping("/caixa")
public class CaixaController {
	
	@Autowired
	private CaixaService service;
	
	@GetMapping
	public List<Caixa> getAll() {
		List<Caixa> allCaixa = service.getAll();
		return allCaixa;
	}
	
	@PostMapping
	public Caixa save(@RequestBody Caixa cxa) {
		cxa.setAbertura(new Date());
		return service.save(cxa);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Caixa cxa) {
		if(service.getIdcxaIdFunc(cxa.getId(), cxa.getIdFuncionarioAbertura())) {
			cxa.setAbertura(new Date());
			service.update(cxa);
			return ResponseEntity.ok(cxa);
		}
		return new ResponseEntity<>("Erro ao atualizar as informações do caixa", HttpStatus.NOT_FOUND);
		
	}
	
	
}
