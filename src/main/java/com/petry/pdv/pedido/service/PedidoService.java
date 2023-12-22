package com.petry.pdv.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.petry.pdv.pedido.entity.Pedido;
import com.petry.pdv.pedido.entity.PedidoResponse;
import com.petry.pdv.pedido.repository.PedidoRepository;
import com.petry.pdv.utils.ErrorResponse;

import jakarta.transaction.Transactional;

@SuppressWarnings("rawtypes")
@Service
@Transactional
public class PedidoService {
	@Autowired
	PedidoRepository repository;
	
	public ResponseEntity savepedidoLoja(Pedido pedido){
		try {
			 Pedido order = repository.save(pedido);
			 return new ResponseEntity<>(new PedidoResponse(order.getId(), "Pedido "), HttpStatus.OK);

		} catch (Exception e) {
			 return new ResponseEntity<>(new ErrorResponse("Erro ao criar pedido!"), HttpStatus.OK);

		}
	}
	
}
