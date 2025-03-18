package com.petry.pdv.proprietario.dto;

import java.util.List;
import java.util.Set;

import com.petry.pdv.assinatura.dto.AssinaturaDTO;
import com.petry.pdv.login.dto.LoginDTO;
import com.petry.pdv.loja.dto.LojaDTO;

import lombok.Builder;
import lombok.Data;

@Data
public class ProprietarioResponseDTO {

	private Long id;
	private String nome;   
   	private String sobrenome;    
    private Long telefone;    
    private AssinaturaDTO assinatura;
    private LoginDTO login;
    private String token;
    private Set<LojaDTO> lojas;

}
