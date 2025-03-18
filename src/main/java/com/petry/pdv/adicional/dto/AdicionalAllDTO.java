package com.petry.pdv.adicional.dto;

import java.math.BigDecimal;

import com.petry.pdv.loja.dto.LojaDTO;

import lombok.Data;

@Data
public class AdicionalAllDTO {
	    private Long id;
	    private String status;
	    private String descricao;
	    private String tag;
	    private String titulo;
	    private BigDecimal valor;
	    private LojaDTO loja;
	    private Long produtoid;
	    
	    public AdicionalAllDTO(Long id, String status, String descricao, String tag, String titulo, BigDecimal valor, LojaDTO loja, Long produtoid) {
	        this.id = id;
	        this.status = status;
	        this.descricao = descricao;
	        this.tag = tag;
	        this.titulo = titulo;
	        this.valor = valor;
	        this.loja = loja;
	        this.produtoid = produtoid;
	    }

}
