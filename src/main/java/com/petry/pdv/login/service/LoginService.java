package com.petry.pdv.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.repository.LoginRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private LoginRepository repository;
	
	
	public List<Login> getAll(){
		return repository.findAll();
	}
	
	public Login save(Login login) {
		return repository.save(login);
		
	}
}	
