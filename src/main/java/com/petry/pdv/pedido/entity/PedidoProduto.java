package com.petry.pdv.pedido.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProduto {

	private Integer codproduto;
	private BigDecimal qtd;
	private Integer idLojaProduto;
	private Long idLojaEntrega;
	private BigDecimal dpi;

}
