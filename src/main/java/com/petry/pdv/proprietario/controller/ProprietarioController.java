package com.petry.pdv.proprietario.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.service.AssinaturaService;
import com.petry.pdv.login.UserTypes;
import com.petry.pdv.login.entity.Acessos;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.service.LoginService;
import com.petry.pdv.proprietario.dto.ProprietarioAtualizacaoDTO;
import com.petry.pdv.proprietario.dto.ProprietarioDTO;
import com.petry.pdv.proprietario.entity.DonoAssinatura;
import com.petry.pdv.proprietario.entity.Proprietario;
import com.petry.pdv.proprietario.service.ProprietarioService;
import com.petry.pdv.utils.Constants;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {
	
	@Autowired
	ProprietarioService service;
	@Autowired
	AssinaturaService assinaturaService;
	@Autowired
	LoginService loginService;
	
	

	@PostMapping
	public ResponseEntity insertDonoWithAssinaturaAndLogin(@RequestBody ProprietarioDTO proprietario) {
		return new ResponseEntity(service.save(proprietario), HttpStatus.OK);
	}
	
	
	@GetMapping
	public  List<ProprietarioDTO> getAll() {
		return service.getAll();
	}
	
	@GetMapping("paginator")
	public Page<Proprietario> paginator(
			@RequestParam(defaultValue = "1", required = true) int page,
			@RequestParam(defaultValue = "1", required = true) int size,
			@RequestParam(defaultValue = "1", required = true) String telefone
			) {
		return  service.paginator(page, size, telefone);
	}
	
	@PutMapping("{id}")
	public ProprietarioDTO editar(	@PathVariable Long id,@RequestBody ProprietarioAtualizacaoDTO dto) throws Exception {
		return  service.update(id, dto);
	}
	
}
