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



	@Column(name = "email" )
	private String email;
}
