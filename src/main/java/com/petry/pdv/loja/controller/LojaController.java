
package com.petry.pdv.loja.controller;

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

import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.loja.service.LojaService;

@RestController
@RequestMapping("/loja")
public class LojaController {
	
	@Autowired
	private LojaService lojaService;
	
	@GetMapping
	public List<Loja> getAll(){
		return lojaService.getAll();
	}
	@PostMapping
	public Loja insert(@RequestBody Loja loja) {
		return lojaService.add(loja);
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Loja loja) {
		if(lojaService.buscarPorId(loja.getIdloja())) {
			 lojaService.add(loja);
			 return ResponseEntity.ok(loja);
		}
		
		return new ResponseEntity<>("Erro ao atualizar a loja", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Loja loja) {
		if(lojaService.buscarPorId(loja.getIdloja())) {
			lojaService.delete(loja);
			 return ResponseEntity.ok(loja);
		}
		
		return new ResponseEntity<>("Erro ao deletar a loja", HttpStatus.NOT_FOUND);
	} 
	
	
}
