package com.petry.pdv.adicional.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.adicional.dto.AdicionalDTO;
import com.petry.pdv.adicional.service.AdicionalService;

@RestController
@RequestMapping("/adicional")
public class AdicionalController {
	
	@Autowired
	private AdicionalService service;
	
	@GetMapping("/{idloja}/{idproduto}/allByProduct")
	public ResponseEntity findAll(@PathVariable Long idloja, @PathVariable Long idproduto,  @RequestParam(defaultValue = "", required = false) String param) {
		return new ResponseEntity(service.listarAdicionais(idloja, idproduto, param), HttpStatus.OK);
	}
	
	@GetMapping("/{idloja}/paginator")
	public ResponseEntity paginator(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long idloja ){
		return new ResponseEntity(service.buscarAdicionaisPaginados(page, size, idloja), HttpStatus.OK);
	}
	
	@PostMapping("/{idloja}")
	public ResponseEntity findAll(@PathVariable Long idloja, @RequestBody AdicionalDTO adicional) {
		return new ResponseEntity(service.save(idloja, adicional), HttpStatus.OK);
	}
	
}
