package com.petry.pdv.login.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.funcionario.repository.FuncionarioRepository;
import com.petry.pdv.login.dto.LoginSenhaDTO;
import com.petry.pdv.login.entity.AtualizarSenha;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.login.repository.LoginRepository;
import com.petry.pdv.login.service.LoginService;
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
	
	
    private final ModelMapper mapper = new ModelMapper();

	
	@GetMapping
	public List<Login> getAll() {
		return service.getAll();
		
	}
	@GetMapping("validate/{usuarioid}")
	public boolean validateUsuario(@PathVariable String usuarioid) {
		return  service.validateUsuario(usuarioid);
	}	
	
	
	@PutMapping
	public ResponseEntity updateSenha(@RequestBody AtualizarSenha user) {
		
		try {
			return service.updateSenha(user);
		} catch (Exception e) {
			return new ResponseEntity(user, HttpStatus.BAD_REQUEST);			
		}
	}
	
//	@PostMapping
//	public ResponseEntity insert(@RequestBody Login login) {
//		login.setPrimeiroacesso(Constants.FlagSimOuNao.SIM);
//		//caso seja funcionario
//		if(login.getFuncionario() != null){
//				return  service.save(login);
//			
//		}if(donoRepository.findById(Long.valueOf(login.getFuncionario().getId())).isPresent()) { 
//
//			return  new ResponseEntity<>(service.save(login), HttpStatus.OK);
//		}else if(login.getUserType().equals(UserTypes.ADMIN)) {
//			return  service.save(login);
//		}else {
//			return new ResponseEntity<>(new ErrorResponse("Dono associado não encontrado!"), HttpStatus.NOT_FOUND);
//		}
//			
//	}
//	
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody LoginSenhaDTO login) {	 
		var usernamePassword = new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getSenha());
		try {
			var auth = authenticationManager.authenticate(usernamePassword);
			String token = tokenService.generateToken((Login) auth.getPrincipal());
			Proprietario proprietario = donoRepository.findByLogin(new Login(login.getUsuario()));			
			mapper.typeMap(Proprietario.class, ProprietarioResponseDTO.class);		
			ProprietarioResponseDTO dto = mapper.map(proprietario, ProprietarioResponseDTO.class);
			dto.setToken(token);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch ( AuthenticationException ex) {
		    throw new AccessDeniedException("Usuário ou senha inválidos", ex);
		}
	}
//	
//	@GetMapping("/autenticar")
//	public ResponseEntity autenticarToken(HttpServletRequest request) {
//		Login principal = (Login) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String token = request.getHeader("Authorization").replaceAll("Bearer", "").trim();
//
//		
//		switch (principal.getUserType()) {
//		case ADMIN: {			
//			return new ResponseEntity<>(Customrepository.buscarInfosAdmin(principal, token), HttpStatus.OK);
//		}
//		case DONO: {			
//			return new ResponseEntity<>(Customrepository.buscarInfosCliente(principal.getUsuario(), token), HttpStatus.OK);
//		}
//		case FUNCIONARIO: {
//			
//			break;
//		}
//		default:
//			return new ResponseEntity<>(new ErrorResponse("Role não configurada!"), HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity(new ErrorResponse("Erro inesperado na autenticação!"), HttpStatus.NOT_FOUND);
//		
//	}
}


//public record LoginResponseDTO(Long userId, List<Loja> lojas, Integer qtdLojas, boolean isAdmin, List<Acessos> acessos ,String token) {


    