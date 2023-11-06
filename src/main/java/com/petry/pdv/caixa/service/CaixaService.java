package com.petry.pdv.caixa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petry.pdv.caixa.entity.Caixa;
import com.petry.pdv.caixa.repository.CaixaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CaixaService {

	@Autowired
	private CaixaRepository repository;
	
	public List<Caixa> getAll() {
		return repository.findAll();
	}

	public Caixa save(Caixa cxa) {
		return repository.save(cxa);
	}

	public boolean getIdcxaIdFunc(Long idCaixa, Long idFuncionario) {
		return repository.getIdCxaAndIdFuncionario(idCaixa.toString(), idFuncionario.toString()) ? true : false;
	}

	public Caixa update(Caixa cxa) {
		 return repository.save(cxa);
	}

}
