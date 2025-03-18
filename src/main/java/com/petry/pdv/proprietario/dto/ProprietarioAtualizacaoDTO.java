package com.petry.pdv.proprietario.dto;

import lombok.Data;

@Data
public class ProprietarioAtualizacaoDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private Long telefone;
    private String status;
}