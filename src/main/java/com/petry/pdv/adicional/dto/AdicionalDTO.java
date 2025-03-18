package com.petry.pdv.adicional.dto;

import java.math.BigDecimal;

import com.petry.pdv.utils.LojaBasicEntityDTO;

import lombok.Data;

@Data
public class AdicionalDTO extends LojaBasicEntityDTO{
	private Long id;
	private String titulo;
	private String descricao;
	private BigDecimal valor;
	private String tag;
	private String status;
}
