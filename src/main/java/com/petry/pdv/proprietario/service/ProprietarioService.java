package com.petry.pdv.proprietario.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
			dto.getLogin().setSenha(passwordEncoder().encode(dto.getLogin().getSenha()));
			mapper.typeMap(LoginDTO.class, Login.class);
			return repository.save(mapper.map(dto, Proprietario.class));
		}

		public List<DonoAssinatura> getAll() {
			List<Proprietario> dono = repository.findAll();
			List<DonoAssinatura> donAssinaturaList = new ArrayList<>();
			Assinatura assinatura = new Assinatura();
			
			preencherInfoDono(dono, donAssinaturaList, assinatura);
			return donAssinaturaList;
		}
		
		private void preencherInfoDono(List<Proprietario> donoList, List<DonoAssinatura> donAssinatura,  Assinatura assinatura) {
			donoList.forEach(dono -> {				
				Assinatura assinaturaResponse = assinaturaService.getAssinaturaByDono(dono);
				if(assinaturaResponse != null ) {	
					DonoAssinatura donoassinatura = new DonoAssinatura();
					donoassinatura.setNome(dono.getNome());
					donoassinatura.setSobrenome(dono.getSobrenome());
					donoassinatura.setDataAbertura(formatData(assinaturaResponse.getDataAbertura()));
					donoassinatura.setDataUltimoPagamento(assinaturaResponse.getDataUltimoPagamento() == null ? "-" : formatData(assinaturaResponse.getDataUltimoPagamento()));
					donoassinatura.setQtdLojas(assinaturaResponse.getQtdLojas());
					donAssinatura.add(donoassinatura); 
				}
			});
			
		}
		private String formatData(Date data) {
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		        String dataFormatada = sdf.format(data);
		        return dataFormatada;
		}
		public Optional<Proprietario> findbyId() {
			return repository.findById(Long.valueOf(1));
		}
		public ResponseEntity verificarPlano(Long id) {
			Optional<Proprietario> dono = repository.findById(id);
			if(dono.isPresent()) {
				//if(dono.get().getQtdLojas() > 0) {
					return new ResponseEntity(new ErrorResponse("Dono com plano válido"), HttpStatus.OK);				
				} else {
					return new ResponseEntity(new ErrorResponse("Seu plano não permite a inclusão de mais uma loja!"), HttpStatus.NOT_ACCEPTABLE);				
				}
		//	} else {
			//	return new ResponseEntity(new ErrorResponse("Dono associado não encontrado!"),  HttpStatus.NOT_ACCEPTABLE);

		//	}
			 
		}

		public void diminuirLojaPlano(Long id) {
			Optional<Proprietario> dono = repository.findById(id);
			if(dono.isPresent()) {
				Proprietario donoObj = dono.get();
				//donoObj.setQtdLojas(donoObj.getQtdLojas() - 1);
				
			} else {
				return;
			}
		}
	
}
