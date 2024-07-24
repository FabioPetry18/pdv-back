package com.petry.pdv.pedido.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petry.pdv.pedido.dto.PedidoProduto;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv", name = "pedidoprod")
public class PedidoProd {
	
	/*
	 * STATUS PEDIDO
	 * 
	 * 1- CRIADO
	 * 2-CONFIRMADO
	 * 3-CANCELADO/NÃO CONFIRMADO
	 * 
	 * */
	
	@EmbeddedId
    private PedidoProdPK id;

	

	@Transient
	private List<PedidoProduto> produto;
	
	@Column(name = "qtdProduto" )
    private BigDecimal qtd; 
	
	@Column(name = "idlojaentrega" )
	private Long idLojaEntrega; 
	
	@Column(name = "idlojaproduto" )
	private Long idLojaProduto; 
	
	@Column(name = "dtpedido" )
	private Date dtpedido; 
	
	@Column(name = "idcliente" )
	private Long idCliente; //o ID vai ser o telefone do cliente.
	
	@Column(name = "idendereco" )
	private Long idEndereco; 
	
	@Column(name = "dpi" )
	private BigDecimal dpi;
	
	@Column(name = "status" )
	private int status;	

	@Column(name = "idfuncionario" )
	private Integer idFuncionario; // quem foi responsavel pela entrega do pedido.
	
	@Column(name = "idformapagamento" )
	private Integer idFormaPagamento;
	

}
