package com.petry.pdv.funcionario.entity;

import com.petry.pdv.login.entity.Login;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(schema = "pdv", name = "funcionario")
public class Funcionario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id; 
	
	@Column(name = "idloja")
    private Long idLoja;
		
	@Column(name = "nome")
	private String nome;
	
    @OneToOne
    @JoinColumn(name = "login_id", nullable = false, unique = true)
    private Login login;	
}
