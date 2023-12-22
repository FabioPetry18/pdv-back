package com.petry.pdv.cliente.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ClientePk implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "telefone")
	private Long telefone;
	
	@Column(name = "idloja")
	private Long idLoja;


}
