package com.petry.pdv.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.pedido.entity.Pedido;
import com.petry.pdv.pedido.entity.PedidoResponse;
import com.petry.pdv.pedido.service.PedidoService;
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@PostMapping
	public ResponseEntity insertPedidoLoja(@RequestBody Pedido pedido) {
		pedido.setLoja(true);
		pedido.setPedidoAceito(true);		
		return service.savepedidoLoja(pedido);
		
	}
	
}
