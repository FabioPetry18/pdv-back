package com.petry.pdv.dono.entity;

import com.petry.pdv.login.UserTypes;

import lombok.Data;

@Data
public class DonoAssinatura {

	private String nome;    
	private String sobrenome;  
    private String senha;
	private Integer qtdLojas;	
	
	
    private String acessos;
    private UserTypes userType = UserTypes.CLIENTE;
    private String idUser; 

   
}
