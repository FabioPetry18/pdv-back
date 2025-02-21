package com.petry.pdv.loja.dto;

import java.util.List;

import com.petry.pdv.configuracao.dto.ConfiguracaoDTO;
import com.petry.pdv.horarioFuncionamento.dto.HorarioFuncionamentoDTO;

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
	private List<HorarioFuncionamentoDTO> horarios;
	private List<ConfiguracaoDTO> configuracao;
}
