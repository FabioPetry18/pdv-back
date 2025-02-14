package com.petry.pdv.loja.dto;

import com.petry.pdv.configuracao.dto.ConfiguracaoDTO;

import lombok.Data;

@Data
public class LojaDTO {
	private Long id;
	private String nome;
	private String endereco;
	private String bairro;
	private String rua;
	private String numero;
	private String cep;
	private String uf;
	private String cidade;
	private Long telefone;
	private ConfiguracaoDTO configuracao;
	
}
