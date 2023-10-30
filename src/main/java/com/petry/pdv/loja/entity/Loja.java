package com.petry.pdv.loja.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "pdv", name = "loja")
public class Loja {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idloja")
	private Long idloja;
	
	@Column(name = "nome")
	private String nome;

	public Long getIdloja() {
		return idloja;
	}

	public void setIdloja(Long idloja) {
		this.idloja = idloja;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Loja(Long idloja, String nome) {
		super();
		this.idloja = idloja;
		this.nome = nome;
	}
	public Loja() {}
	
	
	
}
