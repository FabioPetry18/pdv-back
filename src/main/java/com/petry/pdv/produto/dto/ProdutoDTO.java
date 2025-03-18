package com.petry.pdv.produto.dto;

import java.math.BigDecimal;
import java.util.List;

import com.petry.pdv.adicional.dto.AdicionalDTO;
import com.petry.pdv.utils.LojaBasicEntityDTO;

import lombok.Data;

@Data
public class ProdutoDTO extends LojaBasicEntityDTO {
	private Long id;
	private String titulo;
	private String descricao;
	private BigDecimal valor;
    private List<AdicionalDTO> adicionais;
	private String imagem;
	private String status;	
}
