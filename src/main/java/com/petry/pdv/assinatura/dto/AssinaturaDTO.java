package com.petry.pdv.assinatura.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AssinaturaDTO {
    private int qtdLojas;
    private Date dataAbertura = new Date();
    private Date dataFechamento;
    private Date dataUltimoPagamento;
    private boolean status;
    private ProprietarioDTO proprietario;
}
