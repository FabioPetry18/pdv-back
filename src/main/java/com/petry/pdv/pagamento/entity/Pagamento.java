package com.petry.pdv.pagamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv", name = "pagamento")
public class Pagamento {
	
	@Id
	@Column(name = "idformapagamento")
    private String idFormaPagamento;

    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "status")
    private String status;
}
