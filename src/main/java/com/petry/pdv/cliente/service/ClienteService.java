package com.petry.pdv.cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petry.pdv.cliente.entity.Cliente;
import com.petry.pdv.cliente.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
		public Cliente addCliente(Cliente cliente) {
		
			return repository.save(cliente);
		
		}
	
}
