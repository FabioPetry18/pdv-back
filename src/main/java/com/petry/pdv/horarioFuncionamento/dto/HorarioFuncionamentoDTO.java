package com.petry.pdv.horarioFuncionamento.dto;

import java.time.LocalTime;

import com.petry.pdv.utils.DiaSemanaEnum;

import lombok.Data;

@Data
public class HorarioFuncionamentoDTO {
    private Long id;
    private DiaSemanaEnum diaSemana;
    private LocalTime fechamento;
    private LocalTime abertura;
}
