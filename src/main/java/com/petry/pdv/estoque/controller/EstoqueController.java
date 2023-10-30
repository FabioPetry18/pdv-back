package com.petry.pdv.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.estoque.entity.Estoque;
import com.petry.pdv.estoque.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	
	@Autowired
	private EstoqueService service;
	
	@PostMapping
	public Estoque save(@RequestBody Estoque estoque) {		
		service.saveEstoque(estoque);		
		return estoque;
	}
	
	@GetMapping
	public List<Estoque> getAll(){
		return service.getAllEstoque();		
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Estoque estoque) {
		boolean select = service.findByPkAndLoja(estoque.getIdProduto(), estoque.getIdEstoque(), estoque.getIdLoja());
		if(select) {
			service.update(estoque);
			return ResponseEntity.ok(estoque);
		}
		 return new ResponseEntity<>("Não foi encontrado o produto: " + estoque.getIdProduto() + " da loja " + estoque.getIdLoja(), HttpStatus.NOT_FOUND);		
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Estoque estoque) {
		boolean select = service.findByPkAndLoja(estoque.getIdProduto(), estoque.getIdEstoque(), estoque.getIdLoja());
		if(select) {
			service.delete(estoque);
			return ResponseEntity.ok(estoque);
		}
		 return new ResponseEntity<>("Não foi encontrado o produto: " + estoque.getIdProduto() + " da loja " + estoque.getIdLoja(), HttpStatus.NOT_FOUND);		
	}
	
	
	
	
}
