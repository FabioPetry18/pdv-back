package com.petry.pdv.cliente.entity;

import java.util.List;

import com.petry.pdv.login.entity.Login;
import com.petry.pdv.loja.entity.Loja;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "pdv", name = "cliente")
@Data
public class Cliente {
    

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
	private String nome;

    
    
	//quantidade de lojas que esse cliente tem direito.
	@Column(name = "qtdlojas")
	private Integer qtdLojas;		 	
	
    @OneToMany(mappedBy = "cliente",  fetch = FetchType.LAZY)
  //  @JsonIgnore
    private List<Loja> lojas;
    
 
    
    
}