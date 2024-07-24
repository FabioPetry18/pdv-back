package com.petry.pdv.produto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv",name = "produto" )
public class Produto {
	
	@EmbeddedId
	private ProdutoPK id;
	
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
