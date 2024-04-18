package com.petry.pdv.endereco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petry.pdv.endereco.entity.Endereco;
import com.petry.pdv.endereco.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository repository;
	
	public List<Endereco>buscarEnderecoClienteById(Long id){
		return repository.findByIdClienteAndIdLoja(id);
		
	}
}
