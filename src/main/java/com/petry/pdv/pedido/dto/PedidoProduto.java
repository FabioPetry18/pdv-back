package com.petry.pdv.pedido.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProduto {
	private Integer codproduto;
	private String descricao;
	private BigDecimal qtd;
	private Long idLojaProduto;
	private Long idLojaEntrega;
	private BigDecimal dpi;

}
