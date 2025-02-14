package com.petry.pdv.proprietario.dto;

import java.util.List;

import com.petry.pdv.assinatura.dto.AssinaturaDTO;
import com.petry.pdv.login.dto.LoginDTO;
import com.petry.pdv.loja.dto.LojaDTO;

import lombok.Builder;
import lombok.Data;

@Data
public class ProprietarioDTO {

	private Long id;
	private String nome;   
   	private String sobrenome;    
    private Long telefone;    
    private AssinaturaDTO assinatura;
    private LoginDTO login;
    private List<LojaDTO> lojas;

}
