package com.petry.pdv.proprietario.controller;


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
import com.petry.pdv.login.UserTypes;
import com.petry.pdv.login.entity.Acessos;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.service.LoginService;
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
	
	
	
//	@PostMapping
//	public Proprietario insert(@RequestBody Proprietario dono) {		
//		return service.save(dono);		
//	}
	@PostMapping("/all")
	public ResponseEntity insertDonoWithAssinaturaAndLogin(@RequestBody ProprietarioDTO proprietario) {
		return new ResponseEntity(service.save(proprietario), HttpStatus.OK);
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
		
		return assis;
		
	}
	private Proprietario donoAssinaturaToDono(DonoAssinatura dto) {
		Proprietario dono = new Proprietario();
		dono.setNome(dto.getNome());
		dono.setSobrenome(dto.getSobrenome());
		return dono;
	}
	
	
}
