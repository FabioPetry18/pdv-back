package com.petry.pdv.pedido.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {
	private Long idProduto;
	private Long idLoja;
	private Integer desativated;
	private String descricao;	
	private String descricaocompl;
	private String ean;
	private String img;
}
