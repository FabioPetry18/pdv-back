package com.petry.pdv.pedido.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.petry.pdv.pedido.entity.Pedido;
import com.petry.pdv.pedido.entity.PedidoInseridoEvent;
import com.petry.pdv.pedido.entity.PedidoPK;
import com.petry.pdv.pedido.entity.PedidoProduto;
import com.petry.pdv.pedido.entity.PedidoResponse;
import com.petry.pdv.pedido.repository.PedidoRepository;
import com.petry.pdv.utils.ErrorResponse;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
	@Autowired
	PedidoRepository repository;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	/*
	 * STATUS PEDIDO
	 * 
	 * 1- CRIADO
	 * 2-CONFIRMADO
	 * 3-CANCELADO/NÃO CONFIRMADO
	 * 
	 * */
	
	public ResponseEntity<List<Pedido>> getPedidos(Integer page, Integer size, Integer codloja, Integer status) {
		
	    if (codloja == null || page == null || size == null || status == null) {
	        return new ResponseEntity(new ErrorResponse("page, size e codloja são campos obrigatórios não nulos!"), HttpStatus.BAD_REQUEST);
	    }

	    PageRequest paginacao = PageRequest.of(page, size, Sort.Direction.DESC, "numpedido");
	    List<Pedido> pedidosPage = repository.getByIdloja(codloja, status);

	    
	    return new ResponseEntity<>(pedidosPage, HttpStatus.OK);
	}
	
		
	
	public ResponseEntity savepedidoLoja(Pedido pedido){
		try {
			Long numpedven = repository.getUltimoIndexPedido(pedido.getId().getIdlojaPedido()) + 1;
			for(PedidoProduto produto : pedido.getProduto()) {
				 Pedido novoPedido = new Pedido();
				 PedidoPK pk = new PedidoPK();
				 pk.setCodproduto(produto.getCodproduto());
				 pk.setIdlojaPedido(pedido.getId().getIdlojaPedido());
				 pk.setNumeroPedido(numpedven);
				 novoPedido.setDtpedido(new Date());
				 novoPedido.setIdCliente(pedido.getIdCliente());
				 novoPedido.setDpi(produto.getDpi());
				 novoPedido.setStatus(pedido.getStatus());
				 novoPedido.setIdFuncionario(pedido.getIdFuncionario());
				 novoPedido.setId(pk);
				 novoPedido.setQtd(produto.getQtd());
				 Pedido pedidoSalvo = repository.save(novoPedido);
			}

		
			eventPublisher.publishEvent(new PedidoInseridoEvent(this, pedido));

			 return new ResponseEntity<>(new PedidoResponse(numpedven, "Pedido criado!"), HttpStatus.OK);

		} catch (Exception e) {
			 return new ResponseEntity<>(new ErrorResponse("Erro ao criar pedido!"), HttpStatus.BAD_REQUEST);

		}
	}





	public ResponseEntity confirmPedido(Pedido pedido) {
		 if (repository.existsById(pedido.getId().getNumeroPedido())) {
			 	pedido.setStatus(2);
				repository.save(pedido);
	        } else {
	    		return new ResponseEntity<>(new ErrorResponse("Pedido " + pedido.getId().getNumeroPedido() + " vinculado a loja " +pedido.getId().getIdlojaPedido()+ " não encontrado!"), HttpStatus.BAD_REQUEST);
	        }
	    
		
		return new ResponseEntity<>(new ErrorResponse("Erro ao confirmar pedido!"), HttpStatus.OK);
	}
	public ResponseEntity inativarPedido(Pedido pedido) {
		if (repository.existsById(pedido.getId().getNumeroPedido())) {
			pedido.setStatus(3);
			repository.save(pedido);
		} else {
			return new ResponseEntity<>(new ErrorResponse("Pedido " + pedido.getId().getNumeroPedido()+ " vinculado a loja " +pedido.getId().getNumeroPedido()+ " não encontrado!"), HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>(new ErrorResponse("Erro ao cancelar pedido!"), HttpStatus.OK);
	}
	
}
