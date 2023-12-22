package com.petry.pdv.estoque.entity;

import java.math.BigDecimal;

import com.petry.pdv.funcionario.entity.Funcionario;
import com.petry.pdv.loja.entity.Loja;

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
	private String idFuncionario;
	
	@Column(name = "idcaixa")
	private Long idCaixa;
	
	@Column(name = "qtd")
	private BigDecimal qtd;
	
	@Column(name = "qtdEmbal")
	private BigDecimal qtdEmbal;

	
}
