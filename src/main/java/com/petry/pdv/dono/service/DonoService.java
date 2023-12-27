package com.petry.pdv.dono.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.petry.pdv.dono.entity.Dono;
import com.petry.pdv.dono.repository.DonoRepository;
import com.petry.pdv.utils.ErrorResponse;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DonoService {
	
	@Autowired
	DonoRepository repository;
	
		public Dono addDono(Dono dono) {
		
			return repository.save(dono);
		
		}

		public List<Dono> getAll() {
			return repository.findAll();
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
