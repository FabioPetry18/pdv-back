package com.petry.pdv.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petry.pdv.login.entity.AtualizarSenha;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.repository.LoginRepository;
import com.petry.pdv.utils.ErrorResponse;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoginService implements UserDetailsService{
	
	@Autowired
	private LoginRepository repository;
	
	
	public List<Login> getAll(){
		return repository.findAll();
	}
	public PasswordEncoder passwordEncoder(){
		   return new BCryptPasswordEncoder();
		 }
	public ResponseEntity save(Login login) {
		Optional<Login> in = repository.findById(login.getIdUser());
		login.setSenha(passwordEncoder().encode(login.getSenha()));	

		if(in.isPresent()) {
			return new ResponseEntity(new ErrorResponse("Usuário já cadastrado!"), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity(repository.save(login), HttpStatus.OK);

		}
		
		 
	}
	public ResponseEntity updateSenha(AtualizarSenha user) {
		
		if(repository.existsById(user.getUsuario())) {
			Optional<Login> login = repository.findById(user.getUsuario());
			login.get().setSenha(passwordEncoder().encode(user.getSenha()));
			login.get().setPrimeiroacesso(false);
			return new ResponseEntity<>(repository.save(login.get()), HttpStatus.OK);			
		}
		
		return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);			
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
