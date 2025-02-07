package com.petry.pdv.funcionario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(schema = "pdv", name = "funcionario")
public class Funcionario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id; 
	
	@Column(name = "idloja")
    private Long idLoja;
		
	@Column(name = "nome")
	private String nome;
	
	
}
