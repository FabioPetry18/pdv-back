package com.petry.pdv.funcionario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(schema = "pdv", name = "funcionario")
public class Funcionario {
	@Id
	private String id; //O id do funcionario nao eh AI, eh gerado um UUID para ele para nao dar conflito nos id do dono.
	
	@Column(name = "idloja")
    private Long idLoja;
		
	@Column(name = "nome")
	private String nome;
	
	
}
