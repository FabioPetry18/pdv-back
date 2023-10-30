package com.petry.pdv.caixa.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "pdv", name = "caixa")
public class Caixa {
	
	@Id
	@Column(name = "idcaixa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCaixa;
	
	@Column(name = "dtabertura")
	private Date abertura;
	
	@Column(name = "dtfechamento")
	private Date fechamento;
		
	@Column(name = "idFuncionario")
	private Long idFuncionario;

	public Long getIdCaixa() {
		return idCaixa;
	}

	public void setIdCaixa(Long idCaixa) {
		this.idCaixa = idCaixa;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Caixa(Long idCaixa, Date abertura, Long idFuncionario) {
		super();
		this.idCaixa = idCaixa;
		this.abertura = abertura;
		this.idFuncionario = idFuncionario;
	}
	public Caixa() {}
	
	
	
	
	
}
