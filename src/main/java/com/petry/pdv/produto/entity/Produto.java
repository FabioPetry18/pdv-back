package com.petry.pdv.produto.entity;

import com.petry.pdv.loja.entity.Loja;
import com.petry.pdv.pedido.entity.Pedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv",name = "produto" )
public class Produto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    private Loja loja;
	
   @ManyToOne
   @JoinColumn(name = "pedido_id")
   private Pedido pedido;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "descricaocompl")
	private String descricaoCompl;
	
	@Column(name = "ean")
	private String ean;
	
	@Column(name = "imagem")	
	private byte[] imagem;
	
	@Column(name = "desativated")
	private boolean desativated;	

}
