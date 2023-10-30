package com.petry.pdv.produto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "pdv",name = "produto" )
@ToString
public class Produto {
	@Id
	@Column(name = "idproduto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;
	
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

	public Long getIdProduto() {
		return idProduto;
	}
	
	public boolean isDesativated() {
		return desativated;
	}

	public void setDesativated(boolean desativated) {
		this.desativated = desativated;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoCompl() {
		return descricaoCompl;
	}

	public void setDescricaoCompl(String descricaoCompl) {
		this.descricaoCompl = descricaoCompl;
	}

	

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Produto(Long idProduto, String descricao, String descricaoCompl, String ean, byte[] imagem) {
		super();
		this.idProduto = idProduto;
		this.descricao = descricao;
		this.descricaoCompl = descricaoCompl;
		this.ean = ean;
		this.imagem = imagem;
	}

	public Produto() {

	}

	
	


	
	

}
