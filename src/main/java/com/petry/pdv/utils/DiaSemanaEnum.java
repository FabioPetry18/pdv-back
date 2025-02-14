package com.petry.pdv.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiaSemanaEnum {
	 	SEGUNDA("SEGUNDA", "Segunda"),
	    TERCA("TERÇA", "Terça"),
	    QUARTA("QUARTA", "Quarta"),
	    QUINTA("QUINTA", "Quinta"),
	    SEXTA("SEXTA", "Sexta");

	    private String nome;
	    private String descricao;


	 
}
