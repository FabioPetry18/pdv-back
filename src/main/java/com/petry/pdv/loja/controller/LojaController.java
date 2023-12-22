
package com.petry.pdv.loja.controller;

import java.util.List;
import java.util.Optional;

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

import com.petry.pdv.dono.entity.Dono;
import com.petry.pdv.dono.repository.DonoRepository;
import com.petry.pdv.dono.service.DonoService;
import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.loja.service.LojaService;
import com.petry.pdv.utils.ErrorResponse;

@RestController
@RequestMapping("/loja")
public class LojaController {
	@Autowired
	private DonoService donoService;
	
	@Autowired
	private LojaService lojaService;
	
	
	
	
	
	
	@GetMapping
	public List<Loja> getAll(){
		return lojaService.getAll();
	}
	
	@PostMapping
	public ResponseEntity insert(@RequestBody Loja loja) {
		ResponseEntity dono = donoService.verificarPlano(loja.getIdDono());
		if(dono.getStatusCode() == HttpStatus.OK) {
			donoService.diminuirLojaPlano(loja.getIdDono());
			return new ResponseEntity(lojaService.add(loja), HttpStatus.OK);			
		} else {
			return new ResponseEntity(dono.getBody(), HttpStatus.OK);			
		}

	}
	
	@PutMapping
	public ResponseEntity update(@RequestBody Loja loja) {
		if(lojaService.buscarPorId(loja.getId())) {			 
			 return new ResponseEntity(lojaService.update(loja), HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Erro ao atualizar a loja", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Loja loja) {
		if(lojaService.buscarPorId(loja.getId())) {
			lojaService.delete(loja);
			 return ResponseEntity.ok(loja);
		}
		
		return new ResponseEntity<>("Erro ao deletar a loja", HttpStatus.NOT_FOUND);
	} 
	
	
}
