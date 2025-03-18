package com.petry.pdv.adicional.entity;

import java.math.BigDecimal;

import com.petry.pdv.utils.BasicEntity;
import com.petry.pdv.utils.LojaBasicEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv",name = "adicional" )
public class Adicional extends LojaBasicEntity {

	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "tag")
	private String tag;
}
