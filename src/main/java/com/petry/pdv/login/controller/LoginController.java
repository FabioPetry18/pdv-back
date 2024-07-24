package com.petry.pdv.login.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.dono.repository.DonoRepository;
import com.petry.pdv.funcionario.repository.FuncionarioRepository;
import com.petry.pdv.login.entity.AtualizarSenha;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.repository.LoginRepository;
import com.petry.pdv.login.service.LoginService;
import com.petry.pdv.security.TokenService;
import com.petry.pdv.utils.ErrorResponse;
import com.petry.pdv.utils.LoginResponse;
import com.petry.pdv.utils.query.CustomQuery;

import jakarta.servlet.http.HttpServletRequest;

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
	
	@Autowired
	private  KafkaTemplate<String, String> kafkaTemplate;
	
	

	
	@GetMapping
	public List<Login> getAll() {
		return service.getAll();
		
	}
	
	
	
	@PutMapping
	public ResponseEntity updateSenha(@RequestBody AtualizarSenha user) {
		
		try {
			return service.updateSenha(user);
		} catch (Exception e) {
			return new ResponseEntity(user, HttpStatus.BAD_REQUEST);			
		}
	}
	
	@PostMapping
	public ResponseEntity insert(@RequestBody Login login) {
		login.setPrimeiroacesso(true);
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
		String token = "";
		try {
			var auth = authenticationManager.authenticate(usernamePassword);
			token = tokenService.generateToken((Login) auth.getPrincipal());
		} catch ( AuthenticationException ex) {
		    throw new AccessDeniedException("Usuário ou senha inválidos", ex);
		}
		Login in = repository.findLoginByUsuario(login.getUsuario());
		
		switch (in.getUserType()) {
		case ADMIN: {			
			return new ResponseEntity<>(Customrepository.buscarInfosAdmin(in.getUsuario(), token), HttpStatus.OK);
		}
		case DONO: {			
			LoginResponse re = Customrepository.buscarInfosCliente(in.getUsuario(), token);
			kafkaTemplate.send("login-dono-topic",UUID.randomUUID().toString(), "Produzido: " + re.getUsername());
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
	
	@GetMapping("/autenticar")
	public ResponseEntity autenticarToken(HttpServletRequest request) {
		Login principal = (Login) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = request.getHeader("Authorization").replaceAll("Bearer", "").trim();

		
		switch (principal.getUserType()) {
		case ADMIN: {			
			return new ResponseEntity<>(Customrepository.buscarInfosAdmin(principal.getUsuario(), token), HttpStatus.OK);
		}
		case DONO: {			
			return new ResponseEntity<>(Customrepository.buscarInfosCliente(principal.getUsuario(), token), HttpStatus.OK);
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


    