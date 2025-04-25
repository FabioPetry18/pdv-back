
package com.petry.pdv.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.loja.dto.LojaDTO;
import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.loja.service.LojaService;
import com.petry.pdv.proprietario.service.ProprietarioService;

@RestController
@RequestMapping("/loja")
public class LojaController {
	@Autowired
	private ProprietarioService donoService;
	
	@Autowired
	private LojaService lojaService;


	@GetMapping("{proprietarioid}")
	public List<LojaDTO> getAll(@PathVariable Long proprietarioid){
		return lojaService.getAll(proprietarioid);
	}
	
	@PostMapping("{proprietarioid}")
	public ResponseEntity insert(@RequestBody LojaDTO loja, @PathVariable Long proprietarioid ) throws Exception {
		return new ResponseEntity<>(lojaService.add(loja, proprietarioid), HttpStatus.CREATED);

	}
	@PutMapping("{proprietarioid}")
	public ResponseEntity update(@RequestBody LojaDTO loja, @PathVariable Long proprietarioid ) throws Exception {
		return new ResponseEntity<>(lojaService.update(loja, proprietarioid), HttpStatus.OK);

	}

	
}
