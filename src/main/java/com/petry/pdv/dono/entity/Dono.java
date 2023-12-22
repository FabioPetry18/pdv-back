package com.petry.pdv.dono.entity;

import java.util.List;

import com.petry.pdv.loja.entity.Loja;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(schema = "pdv", name = "dono")
@Data
public class Dono {
    

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
	private String nome;    
    
	//quantidade de lojas que esse cliente tem direito.
	@Column(name = "qtdlojas")
	private Integer qtdLojas;		 	
	
	@Transient
    private List<Loja> lojas;
    
 
    
    
}