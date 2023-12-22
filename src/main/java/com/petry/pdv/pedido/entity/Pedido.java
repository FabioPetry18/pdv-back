package com.petry.pdv.pedido.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import com.petry.pdv.dono.entity.Dono;
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
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv", name = "pedido")
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<Produto> produtos = new ArrayList<>();	
	
	@Column(name = "dtPedido" )
	private Date dtpedido; 
	
	@Column(name = "idcliente" )
	private Long idCliente; //o ID vai ser o telefone do cliente.
	
	@Column(name = "dpi" )
	private BigDecimal dpi;
	
	//Pedido feito pela própria loja.
	@Column(name = "isLoja" )
	private boolean isLoja;
	
	@Column(name = "pedidoaceito" )
	private boolean pedidoAceito;	

	@Column(name = "idfuncionario" )
	private Integer idFuncionario; // quem foi responsavel pela entrega do pedido.
	
	
	 public List<Produto> getProdutos() {
	        produtos.size(); 
	        return produtos;
	    }

}
