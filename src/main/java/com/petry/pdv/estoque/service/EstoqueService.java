package com.petry.pdv.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petry.pdv.estoque.entity.Estoque;
import com.petry.pdv.estoque.repository.EstoqueRespository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstoqueService {
	
	@Autowired
	private EstoqueRespository repository;
	
	public Estoque saveEstoque(Estoque estoque) {
		return repository.save(estoque);	
	}

	public List<Estoque> getAllEstoque() {
		return repository.findAll();
	}

	

	public boolean findByPkAndLoja(Long idProduto, Long idEstoque, Long idLoja) {
		return repository.findByPkAndLoja(idProduto.toString(),idEstoque.toString(),idLoja.toString()) == 1 ? true : false;

	}

	public Estoque update(Estoque estoque) {		
		return repository.save(estoque);		
	}

	public void delete(Estoque estoque) {
		 repository.delete(estoque);
	}
	
	
	
}
