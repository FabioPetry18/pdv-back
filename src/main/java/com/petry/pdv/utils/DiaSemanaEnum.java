package com.petry.pdv.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiaSemanaEnum {
	 	SEGUNDA("Segunda"),
	    TERCA("Terça"),
	    QUARTA("Quarta"),
	    QUINTA("Quinta"),
	    SEXTA("Sexta"),
	    SABADO("Sabado"),
	    DOMINGO("Domingo");

	    private String nome;


	 
}
