package com.petry.pdv.produto.entity;

import java.math.BigDecimal;
import java.util.List;

import com.petry.pdv.adicional.entity.Adicional;
import com.petry.pdv.utils.BasicDateRegister;
import com.petry.pdv.utils.LojaBasicEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv",name = "produto" )
public class Produto extends LojaBasicEntity {
	

	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "imagem")	
	private String imagem;
	
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "produto_adicional", 
        joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private List<Adicional> adicionais;
}
