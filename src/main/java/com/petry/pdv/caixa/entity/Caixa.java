package com.petry.pdv.caixa.entity;

import java.util.Date;

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
@Table(schema = "pdv", name = "caixa")
public class Caixa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    private Loja loja;

	@Column(name = "dtabertura")
	private Date abertura;
	
	@Column(name = "dtfechamento")
	private Date fechamento;
			
	@Column(name = "idFuncionarioAbertura")
	private Long idFuncionarioAbertura;
	
	@Column(name = "idFuncionarioFechamento")
	private Long idFuncionarioFechamento;

	
}
