package com.petry.pdv.estoque.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "pdv",name = "estoque" )
public class Estoque {
	
	@Id
	@Column(name = "idestoque")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstoque;
	
	@Column(name = "idproduto")
	private Long idProduto;
	
	@Column(name = "idloja")
	private Long idLoja;
	
	@Column(name = "idfuncionario")
	private Long idFuncionario;
	
	@Column(name = "idcaixa")
	private Long idCaixa;
	
	@Column(name = "qtd")
	private BigDecimal qtd;
	
	@Column(name = "qtdEmbal")
	private BigDecimal qtdEmbal;

	public Long getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(Long idEstoque) {
		this.idEstoque = idEstoque;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Long idLoja) {
		this.idLoja = idLoja;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getQtdEmbal() {
		return qtdEmbal;
	}

	public void setQtdEmbal(BigDecimal qtdEmbal) {
		this.qtdEmbal = qtdEmbal;
	}

	public Estoque(Long idEstoque, Long idProduto, Long idLoja, BigDecimal qtd, BigDecimal qtdEmbal) {
		super();
		this.idEstoque = idEstoque;
		this.idProduto = idProduto;
		this.idLoja = idLoja;
		this.qtd = qtd;
		this.qtdEmbal = qtdEmbal;
	}
	public Estoque() {}	
	
}
