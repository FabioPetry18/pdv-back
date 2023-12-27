package com.petry.pdv.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.dono.repository.DonoRepository;
import com.petry.pdv.funcionario.repository.FuncionarioRepository;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.repository.LoginRepository;
import com.petry.pdv.login.service.LoginService;
import com.petry.pdv.security.TokenService;
import com.petry.pdv.utils.ErrorResponse;
import com.petry.pdv.utils.LoginResponse;
import com.petry.pdv.utils.query.CustomQuery;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
@SuppressWarnings("rawtypes")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@Autowired
	private DonoRepository donoRepository;
	@Autowired
	private FuncionarioRepository FuncionarioRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private CustomQuery Customrepository;
	@Autowired
	private LoginRepository repository;
	
	

	
	@GetMapping
	public List<Login> getAll() {
		
		return service.getAll();
		
	}
	
	@PostMapping
	public ResponseEntity insert(@RequestBody Login login) {
		//caso seja funcionario
		if(login.getIdUser().contains("-")){
			if(FuncionarioRepository.findById(login.getIdUser()).isPresent()) {
				return  service.save(login);
			} else {
				return new ResponseEntity<>(new ErrorResponse("Funcionario associado não encontrado!"), HttpStatus.NOT_FOUND);
			}
		}if(donoRepository.findById(Long.valueOf(login.getIdUser())).isPresent()) { //caso seja dono

			return  new ResponseEntity<>(service.save(login), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("Dono associado não encontrado!"), HttpStatus.NOT_FOUND);
		}
			
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody Login login) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getSenha());
		var auth = authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((Login) auth.getPrincipal());
		Login in = repository.findLoginByUsuario(login.getUsuario());
		
		switch (in.getUserType()) {
		case ADMIN: {			
			return new ResponseEntity<>(Customrepository.buscarInfosAdmin(in.getUsuario(), token), HttpStatus.OK);
		}
		case CLIENTE: {			
			return new ResponseEntity<>(Customrepository.buscarInfosCliente(in.getUsuario(), token), HttpStatus.OK);
		}
		case FUNCIONARIO: {
			
			break;
		}
		default:
			return new ResponseEntity<>(new ErrorResponse("Role não configurada!"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(new ErrorResponse("Erro inesperado na autenticação!"), HttpStatus.NOT_FOUND);
		
	}
}


//public record LoginResponseDTO(Long userId, List<Loja> lojas, Integer qtdLojas, boolean isAdmin, List<Acessos> acessos ,String token) {


