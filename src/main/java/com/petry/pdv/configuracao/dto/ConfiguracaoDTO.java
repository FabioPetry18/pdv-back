package com.petry.pdv.configuracao.dto;

import com.petry.pdv.utils.DiaSemanaEnum;

import lombok.Data;

@Data

public class ConfiguracaoDTO {
        private Long id;
        private Integer abertura = 8;
        private Integer fechamento = 18;
        private DiaSemanaEnum diasSemana = DiaSemanaEnum.QUARTA;

}
