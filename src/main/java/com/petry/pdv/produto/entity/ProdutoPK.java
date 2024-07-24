package com.petry.pdv.produto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data

public class ProdutoPK {
	
    @Column(name = "codproduto")
	private Long codproduto;
	
	@Column(name = "idloja")
	private Long idloja;
}
