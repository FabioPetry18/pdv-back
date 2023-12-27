package com.petry.pdv.dono.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.service.AssinaturaService;
import com.petry.pdv.dono.entity.Dono;
import com.petry.pdv.dono.entity.DonoAssinatura;
import com.petry.pdv.dono.service.DonoService;
import com.petry.pdv.login.UserTypes;
import com.petry.pdv.login.entity.Acessos;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.service.LoginService;
import com.petry.pdv.utils.ErrorResponse;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/dono")
public class DonoController {
	
	@Autowired
	DonoService service;
	@Autowired
	AssinaturaService assinaturaService;
	@Autowired
	LoginService loginService;
	
	@PostMapping
	public Dono insert(@RequestBody Dono dono) {		
		return service.addDono(dono);		
	}
	@PostMapping("/acesso")
	@Transactional
	public ResponseEntity insertDonoWithAssinaturaAndLogin(@RequestBody DonoAssinatura dono) {	
		try {
			//CRIACAO DO DONO
			Dono obj = donoAssinaturaToDono(dono);
			Dono donoCreate = service.addDono(obj);
			//CRIACAO DA ASSINATURA
			Assinatura assinatura = donoAssinaturaToAssinatura(dono, donoCreate);
			assinaturaService.save(assinatura);
			//CRIACAO DO LOGIN
			Login login = donoAssinaturaToLogin(dono, donoCreate);
			loginService.save(login);
			return new ResponseEntity(new ErrorResponse("Dono, assinatura e login criado - usuario: " + login.getUsuario() + " senha: " + dono.getSenha()), HttpStatus.OK);					
		} catch (Exception e) {
			return new ResponseEntity(new ErrorResponse("erro ao realizar cadastro: " + e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);					
		}
			
 
	}
	
	
	
	@GetMapping
	public  List<Dono> getAll() {
		return service.getAll();
	}
	
	private Login donoAssinaturaToLogin(DonoAssinatura dono, Dono donoCreate) {
		Login login = new Login();
	    login.setUsuario(dono.getNome().trim().concat(".").concat(dono.getSobrenome().trim()));
	    login.setSenha(dono.getSenha());
	    login.setAcessos(Acessos.TodosOsAcessos.getLabel());
	    login.setUserType(UserTypes.DONO);
	    login.setIdUser(donoCreate.getId().toString());
		    
		return login;
	}
	private Assinatura donoAssinaturaToAssinatura(DonoAssinatura dto, Dono donoCreate) {
		Assinatura assis = new Assinatura();
		assis.setDono(donoCreate);
		assis.setQuantidadeLojas(dto.getQtdLojas());
		assis.setStatus(true);
		return assis;
		
	}
	private Dono donoAssinaturaToDono(DonoAssinatura dto) {
		Dono dono = new Dono();
		dono.setNome(dto.getNome());
		dono.setNome(dto.getSobrenome());
		return dono;
	}
	
	
}
