package com.petry.pdv.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.repository.LoginRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoginService implements UserDetailsService{
	
	@Autowired
	private LoginRepository repository;
	
	
	public List<Login> getAll(){
		return repository.findAll();
	}
	
	public Login save(Login login) {
		return repository.save(login);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsuario(username);
	}

	

//	public Login autenticar(String usuario, String senha) {
//		Login login =  repository.findByUsuarioAndSenha(usuario, senha);
//
//		return login;

//	}

	
}	
