package com.petry.pdv.dono.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.service.AssinaturaService;
import com.petry.pdv.dono.entity.Proprietario;
import com.petry.pdv.dono.entity.DonoAssinatura;
import com.petry.pdv.dono.service.DonoService;
import com.petry.pdv.login.UserTypes;
import com.petry.pdv.login.entity.Acessos;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.service.LoginService;
import com.petry.pdv.utils.Constants;
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
	public Proprietario insert(@RequestBody Proprietario dono) {		
		return service.save(dono);		
	}
	@PostMapping("/acesso")
	@Transactional
	public ResponseEntity insertDonoWithAssinaturaAndLogin(@RequestBody DonoAssinatura dono) {	
		try {
			//CRIACAO DO DONO
			Proprietario donoCreate = service.save(donoAssinaturaToDono(dono));
			//CRIACAO Da Assinatura
			assinaturaService.save(donoAssinaturaToAssinatura(dono, donoCreate));
			//CRIACAO DO LOGIN
			Login login = (Login) loginService.save(donoAssinaturaToLogin(dono, donoCreate)).getBody();
			
			return new ResponseEntity(new ErrorResponse("Dono, assinatura e login criado - usuario: " + login.getUsuario() + " senha: " + dono.getSenha()), HttpStatus.OK);					
		} catch (Exception e) {
			return new ResponseEntity(new ErrorResponse("erro ao realizar cadastro: " + e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);					
		}
			
 
	}
	
	
	
	
	@GetMapping
	public  List<DonoAssinatura> getAll() {
		return service.getAll();
	}
	
	private Login donoAssinaturaToLogin(DonoAssinatura dono, Proprietario donoCreate) {
		Login login = new Login();
	    login.setUsuario(dono.getNome().trim().concat(".").concat(dono.getSobrenome().trim()));
	    login.setPrimeiroacesso(Constants.FlagSimOuNao.SIM);
	    login.setSenha(dono.getSenha());
	    login.setAcessos(Acessos.TodosOsAcessos.getLabel());
	    login.setUserType(UserTypes.DONO);
	    //login.setIdUser(donoCreate.getId().toString());
		    
		return login;
	}
	private Assinatura donoAssinaturaToAssinatura(DonoAssinatura dto, Proprietario donoCreate) {
		Assinatura assis = new Assinatura();
		assis.setId(donoCreate.getId());
		assis.setQtdLojas(dto.getQtdLojas());
		assis.setStatus(true);
		
		return assis;
		
	}
	private Proprietario donoAssinaturaToDono(DonoAssinatura dto) {
		Proprietario dono = new Proprietario();
		dono.setNome(dto.getNome());
		dono.setSobrenome(dto.getSobrenome());
		return dono;
	}
	
	
}
