package com.petry.pdv.configuracao.entity;

import com.petry.pdv.dono.entity.Dono;
import com.petry.pdv.loja.entity.Loja;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(schema = "pdv", name = "configuracao")
public class Configuracao {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "idloja")
        private Loja loja;

        @Column(name = "permissao")
        private boolean funcionarioPermitido;

        @Column(name = "aberturacaixa")
        private boolean aberturaDoCaixa;

        @Column(name = "dtAberturaLoja")
        private Date dataAberturaLoja;

        @Column(name = "dtFechamentoLoja")
        private Date dataFechamentoLoja;

        @Column(name = "diasSemana")
        private String diasSemana;

}
