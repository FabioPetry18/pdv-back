package com.petry.pdv.funcionario.entity;

import com.petry.pdv.login.entity.Login;
import com.petry.pdv.loja.entity.Loja;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(schema = "pdv", name = "funcionario")
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja_id")
    private Loja loja;
	
	@OneToOne(mappedBy = "funcionario", fetch = FetchType.LAZY)
    private Login login;
	
	@Column(name = "nome")
	private String nome;
	
	
	
	
	
	
	
}
