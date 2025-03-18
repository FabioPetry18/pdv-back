package com.petry.pdv.login.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petry.pdv.login.dto.LoginComRelacionamentoDTO;
import com.petry.pdv.login.dto.LoginDTO;
import com.petry.pdv.login.entity.AtualizarSenha;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.repository.LoginRepository;
import com.petry.pdv.utils.Constants;
import com.petry.pdv.utils.ErrorResponse;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoginService implements UserDetailsService{
	
	@Autowired
	private LoginRepository repository;
	private final ModelMapper mapper = new ModelMapper();

	
	public List<Login> getAll(){
		return repository.findAll();
	}
	
	public boolean validateUsuario(String usuarioid) {
		return repository.findLoginByUsuario(usuarioid) != null;
	}
	
	public PasswordEncoder passwordEncoder(){
		   return new BCryptPasswordEncoder();
		 }
	public ResponseEntity save(Login login) {
		login.setSenha(passwordEncoder().encode(login.getSenha()));	
			return new ResponseEntity(new ErrorResponse("Usuário já cadastrado!"), HttpStatus.CONFLICT); 
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsuario(username);
	}

	public Login findByUsuario(String usuario) {
		return 	repository.findLoginByUsuario(usuario);
	}
	
	public LoginDTO atualizar(Long id, LoginDTO dto) throws Exception {
		dto.setSenha(passwordEncoder().encode(dto.getSenha()));	
		mapper.typeMap(LoginDTO.class, Login.class);
		 Login entity = repository.findById(id)
		            .orElseThrow(() -> new Exception("Não encontrado"));
		    mapper.map(dto, entity);
		    repository.save(entity);
		    return dto;
	}
	

//	public Login autenticar(String usuario, String senha) {
//		Login login =  repository.findByUsuarioAndSenha(usuario, senha);
//
//		return login;

//	}

	
}	
