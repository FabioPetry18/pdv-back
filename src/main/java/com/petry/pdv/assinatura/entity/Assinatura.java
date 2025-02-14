package com.petry.pdv.assinatura.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(schema = "pdv", name = "assinatura")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "qtdLojas")	
    private int qtdLojas;

    @Column(name = "dtAbertura")
    private Date dataAbertura = new Date();

    @Column(name = "dtFechamento")
    private Date dataFechamento;

    @Column(name = "dtUltimoMes")
    private Date dataUltimoPagamento; // inicio e fechamento do plano

    @Column(name = "status", columnDefinition = "CHAR(1) DEFAULT 'N' CHECK (status IN ('S', 'N'))", nullable = false)
    private String status;

}
