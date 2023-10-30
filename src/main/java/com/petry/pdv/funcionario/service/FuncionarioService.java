package com.petry.pdv.funcionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petry.pdv.funcionario.entity.Funcionario;
import com.petry.pdv.funcionario.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repository;
	
	
	public List<Funcionario> getAllFuncionarios() {
		return repository.findAll();
	}


	public Funcionario saveFuncionario(Funcionario funcionario) {
		return repository.save(funcionario);
	}


//	public boolean findById(Long idFuncionario) {
//		return repository.getfuncionarioById(idFuncionario.toString()) == 1 ? true : false;
//	}

}
