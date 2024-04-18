package com.petry.pdv.endereco.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv", name = "endereco")
public class Endereco {
	@EmbeddedId
	private EnderecoPK pk;
	
	@Column(name = "nome" )
	private String nome;
	
	@Column(name = "rua" )
	private String rua;
	
	@Column(name = "bairro" )
	private String bairro;
	
	@Column(name = "numero" )
	private Integer numero;
	
	@Column(name = "cep" )
	private Integer cep;
}
