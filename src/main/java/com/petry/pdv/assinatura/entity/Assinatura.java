package com.petry.pdv.assinatura.entity;

import com.petry.pdv.dono.entity.Dono;
import com.petry.pdv.loja.entity.Loja;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(schema = "pdv", name = "assinatura")
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddono")
    private Dono dono;

    @Column(name = "qtdLojas")
    private int quantidadeLojas;

    @Column(name = "dtAbertura")
    private Date dataAbertura;

    @Column(name = "dtFechamento")
    private Date dataFechamento;

    @Column(name = "dtUltimoMes")
    private Date dataUltimoPagamento;

    @Column(name = "status")
    private boolean status;
}
