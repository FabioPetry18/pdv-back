package com.petry.pdv.loja.entity;

import java.util.List;
import java.util.Set;

import com.petry.pdv.configuracao.entity.Configuracao;
import com.petry.pdv.horarioFuncionamento.entity.HorarioFuncionamento;
import com.petry.pdv.pedido.entity.Pedido;
import com.petry.pdv.produto.entity.Produto;
import com.petry.pdv.proprietario.entity.Proprietario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(schema = "pdv", name = "loja")
public class Loja {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "bairro")
	private String bairro;
	
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "cep",length = 8)
	private String cep;
	
	@Column(name = "uf")
	private String uf;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "telefone")
	private Long telefone;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proprietario_id")
	private Proprietario proprietario;
	
	 @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	 @JoinColumn(name = "loja_id")
	 private Set<Configuracao> configuracao;
	 
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
    @JoinColumn(name = "loja_id")
    private Set<HorarioFuncionamento> horarios;
    
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) 
//    @JoinColumn(name = "loja_id")
//    private List<Pedido> pedido;
}
