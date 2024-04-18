package com.petry.pdv.dono.entity;

import java.util.Date;

import com.petry.pdv.login.UserTypes;

import lombok.Data;

@Data
public class DonoAssinatura {

	private String nome;    
	private String sobrenome;  
    private String senha;
	private Integer qtdLojas;	
	
	
    private String acessos;
    private UserTypes userType = UserTypes.DONO;
    private String idUser; 
    
    //para a listagem
    private String dataAbertura;
    private String dataUltimoPagamento;
    private String Status;

   
}
