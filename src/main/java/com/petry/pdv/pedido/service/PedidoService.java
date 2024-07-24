package com.petry.pdv.pedido.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.petry.pdv.pedido.dto.PedidoProduto;
import com.petry.pdv.pedido.dto.PedidoResponse;
import com.petry.pdv.pedido.dto.PesquisaPedidoResponse;
import com.petry.pdv.pedido.entity.Pedido;
import com.petry.pdv.pedido.entity.PedidoInseridoEvent;
import com.petry.pdv.pedido.entity.PedidoPK;
import com.petry.pdv.pedido.entity.PedidoProd;
import com.petry.pdv.pedido.entity.PedidoProdPK;
import com.petry.pdv.pedido.repository.PedidoProdRepository;
import com.petry.pdv.pedido.repository.PedidoRepository;
import com.petry.pdv.utils.ErrorResponse;
import com.petry.pdv.utils.GeralResporitory;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
	@Autowired
	PedidoProdRepository repository;
	@Autowired
	PedidoRepository pedRepository;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	@Autowired
	GeralResporitory geralRepository;
	
	/*
	 * STATUS PEDIDO
	 * 
	 * 1- CRIADO
	 * 2-CONFIRMADO
	 * 3-CANCELADO/NÃO CONFIRMADO
	 * 
	 * */
	
	public ResponseEntity getPedidos(Integer page, Integer size, Integer codloja, Integer status) {
		
	    if (codloja == null || page == null || size == null || status == null) {
	        return new ResponseEntity(new ErrorResponse("page, size e codloja são campos obrigatórios não nulos!"), HttpStatus.BAD_REQUEST);
	    }
	    List<Pedido> pedido = pedRepository.getByIdloja(codloja, status);
	    PageRequest paginacao = PageRequest.of(page, size, Sort.Direction.DESC, "numpedido");
	    
	    List<PesquisaPedidoResponse> produtos = geralRepository.getByIdlojaAndNomeProduto(codloja, status);	    	
	   for(Pedido ped : pedido) {		   
		   ped.setProdutos(produtos.stream().filter(prod-> ped.getId().getNumeroPedido() == prod.getNumpedido()).collect(Collectors.toList()));
	   }
	   
	    return new ResponseEntity<>(pedido, HttpStatus.OK);
	}
	
		



	public ResponseEntity savepedidoLoja(PedidoProd pedido){
		try {
		
			Long numpedven = pedRepository.getUltimoIndexPedido(pedido.getId().getIdlojaPedido()) + 1;
			Pedido novoPedido = salvarPedido(pedido,numpedven);
			salvarPedidoProd(pedido, numpedven);
			
			eventPublisher.publishEvent(new PedidoInseridoEvent(this, novoPedido));

			 return new ResponseEntity<>(new PedidoResponse(numpedven, "Pedido criado!"), HttpStatus.OK);

		} catch (Exception e) {
			 return new ResponseEntity<>(new ErrorResponse("Erro ao criar pedido!"), HttpStatus.BAD_REQUEST);

		}
	}

	private void salvarPedidoProd(PedidoProd pedido, Long numpedven) {
		for(PedidoProduto produto : pedido.getProduto()) {
			 PedidoProdPK pk = new PedidoProdPK();
			 PedidoProd novoPedido = new PedidoProd();
			 pk.setCodproduto(produto.getCodproduto());
			 pk.setIdlojaPedido(pedido.getId().getIdlojaPedido());
			 pk.setNumeroPedido(numpedven);
			 novoPedido.setDtpedido(new Date());
			 novoPedido.setIdCliente(pedido.getIdCliente());
			 novoPedido.setDpi(produto.getDpi());
			 novoPedido.setStatus(pedido.getStatus());
			 novoPedido.setIdFuncionario(pedido.getIdFuncionario());
			 novoPedido.setId(pk);
			 novoPedido.setIdEndereco(pedido.getIdEndereco());
			 novoPedido.setQtd(produto.getQtd());
			 novoPedido.setIdFormaPagamento(pedido.getIdFormaPagamento());
			 PedidoProd pedidoSalvo = repository.save(novoPedido);
		}
	}



	private Pedido salvarPedido(PedidoProd pedido, Long numpedven) {
		 PedidoPK pk = new PedidoPK();
		 pk.setIdlojaPedido(pedido.getId().getIdlojaPedido());
		 pk.setNumeroPedido(numpedven);
		 Pedido novoPedidoPed = new Pedido();
		 novoPedidoPed.setId(pk);
		 novoPedidoPed.setDtpedido(new Date());
		 //novoPedidoPed.setDpi(null);
		 novoPedidoPed.setIdCliente(pedido.getIdCliente());
		 novoPedidoPed.setIdEndereco(pedido.getIdEndereco());
		 novoPedidoPed.setIdFuncionario(pedido.getIdFuncionario());
		 novoPedidoPed.setIdFormaPagamento(pedido.getIdFormaPagamento());
		 novoPedidoPed.setStatus(pedido.getStatus());
		return  pedRepository.save(novoPedidoPed);
	}



	public ResponseEntity confirmPedido(PedidoProd pedido) {
		 if (repository.existsById(pedido.getId().getNumeroPedido())) {
			 	pedido.setStatus(2);
				repository.save(pedido);
	        } else {
	    		return new ResponseEntity<>(new ErrorResponse("Pedido " + pedido.getId().getNumeroPedido() + " vinculado a loja " +pedido.getId().getIdlojaPedido()+ " não encontrado!"), HttpStatus.BAD_REQUEST);
	        }
	    
		
		return new ResponseEntity<>(new ErrorResponse("Erro ao confirmar pedido!"), HttpStatus.OK);
	}
	public ResponseEntity inativarPedido(PedidoProd pedido) {
		if (repository.existsById(pedido.getId().getNumeroPedido())) {
			pedido.setStatus(3);
			repository.save(pedido);
		} else {
			return new ResponseEntity<>(new ErrorResponse("Pedido " + pedido.getId().getNumeroPedido()+ " vinculado a loja " +pedido.getId().getNumeroPedido()+ " não encontrado!"), HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>(new ErrorResponse("Erro ao cancelar pedido!"), HttpStatus.OK);
	}
	
}
