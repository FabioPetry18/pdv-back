package com.petry.pdv.proprietario.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.service.AssinaturaService;
import com.petry.pdv.login.dto.LoginDTO;
import com.petry.pdv.login.entity.Login;
import com.petry.pdv.proprietario.dto.ProprietarioDTO;
import com.petry.pdv.proprietario.entity.DonoAssinatura;
import com.petry.pdv.proprietario.entity.Proprietario;
import com.petry.pdv.proprietario.repository.ProprietarioRepository;
import com.petry.pdv.utils.ErrorResponse;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;

@Service
@Transactional
public class ProprietarioService {
	
	@Autowired
	ProprietarioRepository repository;
	
	@Autowired
	AssinaturaService assinaturaService;

	
    private final ModelMapper mapper = new ModelMapper();
    
    
    public PasswordEncoder passwordEncoder(){
		   return new BCryptPasswordEncoder();
		 }
	
		public Proprietario save(ProprietarioDTO dto) {
			dto.setId(null);
			dto.getLogin().setSenha(passwordEncoder().encode(dto.getLogin().getSenha()));
			mapper.typeMap(LoginDTO.class, Login.class);
			return repository.save(mapper.map(dto, Proprietario.class));
		}

		public List<ProprietarioDTO> getAll() {
			List<Proprietario> proprietarios = repository.findAll();
			ProprietarioDTO dto = new ProprietarioDTO();
			List<ProprietarioDTO> listaDto = new ArrayList<>();
			 
			for(Proprietario proprietario :  proprietarios) {
				mapper.typeMap(Proprietario.class, ProprietarioDTO.class);
				dto = mapper.map(proprietario, ProprietarioDTO.class);
				listaDto.add(dto);				
			}
			return listaDto;				
		}
		
		public Page<Proprietario> paginator(int page, int size, String telefone) {
			
			 PageRequest pageRequest = PageRequest.of(
					    page, 
					    size, 
					    Sort.Direction.ASC, 
					    "telefone"
					);

				Page<Proprietario> proprietarios = repository.findByTelefone(Long.valueOf(telefone), pageRequest);
				return proprietarios;
			}

		public ProprietarioDTO update(ProprietarioDTO dto) {
				if(repository.existsById(dto.getId())) {
					mapper.typeMap(ProprietarioDTO.class, Proprietario.class);
					repository.save(mapper.map(dto, Proprietario.class));					
					return null;
				}
				return null;
		}	
}
