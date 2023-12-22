package com.petry.pdv.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petry.pdv.dono.entity.Dono;
import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.loja.repository.LojaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LojaService {
	
	@Autowired
	private LojaRepository repository;
	

	
	public List<Loja> getAll(){
		return repository.findAll();
	}

	public Loja add(Loja loja) {	
		Loja loj =  repository.save(loja);
		return loj;
		
	}

	public boolean buscarPorId(Long id) {
		
		return repository.buscarPorId(id.toString()) == 1 ? true : false;
	}

	public void delete(Loja loja) {
		 repository.delete(loja);
		
	}

	public Loja update(Loja loja) {
		return repository.save(loja);
		
	}
}
