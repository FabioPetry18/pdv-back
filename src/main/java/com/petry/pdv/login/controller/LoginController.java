package com.petry.pdv.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.service.LoginService;

@RestController
@RequestMapping(name = "/login")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@GetMapping
	public List<Login> getAll() {
		
		return service.getAll();
		
	}
	
	@PostMapping
	public Login insert(@RequestBody Login login) {
		return service.save(login);
	}
}
