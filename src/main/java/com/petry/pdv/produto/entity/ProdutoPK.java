package com.petry.pdv.produto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPK {
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codproduto")
	private Long codproduto;
	
	@Column(name = "idloja")
	private Long idloja;
}
