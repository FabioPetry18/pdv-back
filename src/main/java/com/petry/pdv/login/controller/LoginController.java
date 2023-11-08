package com.petry.pdv.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.entity.LoginResponseDTO;
import com.petry.pdv.login.service.LoginService;
import com.petry.pdv.security.TokenService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	
	public PasswordEncoder passwordEncoder(){
	   return new BCryptPasswordEncoder();
	 }
	
	@GetMapping
	public List<Login> getAll() {
		
		return service.getAll();
		
	}
	
	@PostMapping
	public Login insert(@RequestBody Login login) {
		login.setSenha(passwordEncoder().encode(login.getSenha()));
		return service.save(login);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody Login login) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getSenha());
		var auth = authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((Login) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
		
	}
}
