package com.petry.pdv.loja.entity;

import java.util.List;

import com.petry.pdv.dono.entity.Dono;
import com.petry.pdv.estoque.entity.Estoque;
import com.petry.pdv.funcionario.entity.Funcionario;
import com.petry.pdv.produto.entity.Produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
@Data
@Entity
@Table(schema = "pdv", name = "loja")
public class Loja {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "iddono")
	private Long idDono;
    
	@Transient
    private List<Produto> produtos;
    
	@Transient 
	private List<Funcionario> funcionarios;
    
	@Transient
	private List<Estoque> estoques;
    
//    @OneToMany(mappedBy = "loja")
//    private List<Pedido> pedidos;
    
    
	
}
