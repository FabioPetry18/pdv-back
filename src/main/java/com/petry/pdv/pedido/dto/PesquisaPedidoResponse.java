package com.petry.pdv.pedido.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PesquisaPedidoResponse {
	private Long numpedido;
	
	private String descricao;	
	private Long codproduto;
	private BigDecimal qtd;
	private String produtos;
	private String endereco;
	private String nomCli;
	private String dtPedido;

}
