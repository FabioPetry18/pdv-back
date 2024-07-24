package com.petry.pdv.produto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {
	
	
	private Long idloja;
	
	private Long codproduto;
	
	private String descricao;
	
	private String descricaoCompl;
	
	private String ean;
	
	private byte[] imagem;
	
	private boolean desativated;	
}
