package com.petry.pdv.utils;

import java.util.List;

import com.petry.pdv.loja.entity.Loja;

import lombok.Data;

@Data
public class LoginResponse {

	private String userId;
	private String username;
	private List<Loja> lojas;
	private Integer qtdLojas;
	private String userType;
	private List<Acessos> acessos;
	private String token;
	
}
