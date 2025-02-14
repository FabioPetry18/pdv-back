package com.petry.pdv.configuracao.entity;

import com.petry.pdv.utils.DiaSemanaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "pdv", name = "configuracao")
public class Configuracao {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "habertura")
        private Integer abertura = 8;

        @Column(name = "hfechamento")
        private Integer fechamento = 18;

        @Column(name = "dia_semana")
        @Enumerated(EnumType.STRING)
        private DiaSemanaEnum diasSemana = DiaSemanaEnum.QUARTA;

}
