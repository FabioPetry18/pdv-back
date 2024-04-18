package com.petry.pdv.pedido.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PedidoPK implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numpedido")
    private Long numeroPedido;

    @Column(name = "codproduto")
    private Integer codproduto;
    
	@Column(name = "idlojapedido" )
	private Long idlojaPedido;
	
    

}
