package com.petry.pdv.login.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.collection.spi.PersistentSet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.funcionario.repository.FuncionarioRepository;
import com.petry.pdv.login.dto.LoginDTO;
import com.petry.pdv.login.dto.LoginSenhaDTO;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.repository.LoginRepository;
import com.petry.pdv.login.service.LoginService;
import com.petry.pdv.loja.dto.LojaDTO;
import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.proprietario.dto.ProprietarioResponseDTO;
import com.petry.pdv.proprietario.entity.Proprietario;
import com.petry.pdv.proprietario.repository.ProprietarioRepository;
import com.petry.pdv.security.TokenService;
import com.petry.pdv.utils.query.CustomQuery;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
@SuppressWarnings("rawtypes")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@Autowired
	private ProprietarioRepository donoRepository;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    private final ModelMapper mapper = new ModelMapper();

	
	@GetMapping
	public List<Login> getAll() {
		return service.getAll();
		
	}
	
	@GetMapping("validate/{usuarioid}")
	public boolean validateUsuario(@PathVariable String usuarioid) {
		return  service.validateUsuario(usuarioid);
	}	
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody LoginSenhaDTO login) {	 
		var usernamePassword = new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getSenha());
		try {
			System.out.println("Tentando autenticar usuário: " + login.getUsuario());
			Login es = service.findByUsuario(login.getUsuario());
			var auth = authenticationManager.authenticate(usernamePassword);
			String token = tokenService.generateToken((Login) auth.getPrincipal());
			Login loginEntity = service.findByUsuario(login.getUsuario());
			Proprietario proprietario = donoRepository.findByLogin(loginEntity);
			mapper.typeMap(Proprietario.class, ProprietarioResponseDTO.class);
		    mapper.typeMap(Loja.class, LojaDTO.class).addMappings(mapper -> {
		        mapper.skip(LojaDTO::setConfiguracao); 
		        mapper.skip(LojaDTO::setHorarios); 
		    });
			ProprietarioResponseDTO dto = mapper.map(proprietario, ProprietarioResponseDTO.class);
			dto.setToken(token);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch ( AuthenticationException ex) {
		    throw new AccessDeniedException("Usuário ou senha inválidos", ex);
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody LoginDTO dto) throws Exception {
		return new ResponseEntity<>(service.atualizar(id, dto), HttpStatus.OK);
	}

}




    