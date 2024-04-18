package com.petry.pdv.utils;

import java.util.List;

import com.petry.pdv.funcionario.entity.Funcionario;
import com.petry.pdv.loja.entity.Loja;

import lombok.Data;

@Data
public class LoginResponse {

	private String id;
	private String username;
	private List<Loja> lojas;
	private Loja visualizacaoLoja;
	private Integer qtdLojas;
	private String userType;
	private List<Acessos> acessos;
	private List<Funcionario> funcionarios;
	private String token;
	private boolean primeiroAcesso;
	
}
