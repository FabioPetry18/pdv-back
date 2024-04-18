package com.petry.pdv.endereco.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class EnderecoPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "id" )
    private Long id;
	
	@Column(name = "idcliente" )
	private Long idCliente;
	

}
