package com.petry.pdv.funcionario.dto;

import com.petry.pdv.login.dto.LoginDTO;

import lombok.Data;

@Data
public class FuncionarioDTO {
	private Long  id; 
    private Long idLoja;
	private String nome;
    private LoginDTO login;
}
