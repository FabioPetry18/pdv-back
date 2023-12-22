package com.petry.pdv.cliente.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv", name = "cliente")
public class Cliente  {
	
	@EmbeddedId
	private ClientePk pk;
	
	
	@Column(name = "nomcli")
	private String nomeCliente;
	
	/*      ENDEREÇO      */
	@Column(name = "rua" )
	private String rua;
	
	@Column(name = "bairro" )
	private String bairro;
	
	@Column(name = "numero" )
	private Integer numero;
	
	@Column(name = "cep" )
	private Integer cep;

	@Column(name = "cpf" )
	private Long cpf;

}
