package com.petry.pdv.cliente.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Embeddable
@Data
public class ClientePk implements Serializable {
	private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Long idCliente;
	
	@Column(name = "telefone")
	private Long telefone;
	
	@Column(name = "cpf" )
	private Long cpf;
	
	@Column(name = "idloja")
	private Long idLoja;


}
