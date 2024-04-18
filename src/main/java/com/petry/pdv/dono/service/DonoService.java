package com.petry.pdv.dono.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.petry.pdv.assinatura.entity.Assinatura;
import com.petry.pdv.assinatura.service.AssinaturaService;
import com.petry.pdv.dono.entity.Dono;
import com.petry.pdv.dono.entity.DonoAssinatura;
import com.petry.pdv.dono.repository.DonoRepository;
import com.petry.pdv.utils.ErrorResponse;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DonoService {
	
	@Autowired
	DonoRepository repository;
	@Autowired
	AssinaturaService assinaturaService;
	
	
		public Dono addDono(Dono dono) {
		
			return repository.save(dono);
		
		}

		public List<DonoAssinatura> getAll() {
			List<Dono> dono = repository.findAll();
			List<DonoAssinatura> donAssinaturaList = new ArrayList<>();
			Assinatura assinatura = new Assinatura();
			
			preencherInfoDono(dono, donAssinaturaList, assinatura);
			return donAssinaturaList;
		}
		
		private void preencherInfoDono(List<Dono> donoList, List<DonoAssinatura> donAssinatura,  Assinatura assinatura) {
			donoList.forEach(dono -> {				
				Assinatura assinaturaResponse = assinaturaService.getAssinaturaByDono(dono);
				if(assinaturaResponse != null ) {	
					DonoAssinatura donoassinatura = new DonoAssinatura();
					donoassinatura.setNome(dono.getNome());
					donoassinatura.setSobrenome(dono.getSobrenome());
					donoassinatura.setDataAbertura(formatData(assinaturaResponse.getDataAbertura()));
					donoassinatura.setDataUltimoPagamento(assinaturaResponse.getDataUltimoPagamento() == null ? "-" : formatData(assinaturaResponse.getDataUltimoPagamento()));
					donoassinatura.setQtdLojas(assinaturaResponse.getQuantidadeLojas());
					donoassinatura.setStatus(assinaturaResponse.isStatus() ? "Ativo" : "Inativo");
					donAssinatura.add(donoassinatura); 
				}
			});
			
		}
		private String formatData(Date data) {
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		        String dataFormatada = sdf.format(data);
		        return dataFormatada;
		}
		public Optional<Dono> findby() {
			return repository.findById(Long.valueOf(1));
		}
		public ResponseEntity verificarPlano(Long id) {
			Optional<Dono> dono = repository.findById(id);
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
			Optional<Dono> dono = repository.findById(id);
			if(dono.isPresent()) {
				Dono donoObj = dono.get();
				//donoObj.setQtdLojas(donoObj.getQtdLojas() - 1);
				
			} else {
				return;
			}
		}
	
}
